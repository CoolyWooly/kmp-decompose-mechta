package city_select.presentation

import city_select.data.CitySelectRepository
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import core.domain.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import city_select.domain.model.CityModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import utils.Coordinates
import utils.WrappedSharedFlow

class CitySelectComponent(
    componentContext: ComponentContext,
    private val onSendResult: (CityModel) -> Unit,
) : ComponentContext by componentContext, KoinComponent {

    private val citySelectRepository by inject<CitySelectRepository>()
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private val _state = MutableValue(State())
    val state: Value<State> = _state
    private val _effect = MutableSharedFlow<Effect>()
    val effect: WrappedSharedFlow<Effect> = WrappedSharedFlow(_effect.asSharedFlow())

    private var latitude: Double? = null
    private var longitude: Double? = null
    private var cities: List<CityModel> = emptyList()

    init {
        getCityList()
    }

    fun onEvent(event: Event) {
        when (event) {

            is Event.OnCoordinatesFetch -> {
                getEstimatedCity(event.lat, event.lon)
            }

            is Event.OnCityClick -> {
                saveCity()
                onSendResult(event.city)
            }

            is Event.OnTextSearchChange -> {
                filterCityList(textSearch = event.text)
            }
        }
    }

    private fun getCityList() {
        scope.launch {
            _state.update { it.copy(isLoading = true) }
            when (val data = citySelectRepository.getCities()) {
                is Result.Error -> {
                    _effect.emit(Effect.ShowToast(data.text))
                }

                is Result.Success -> {
                    cities = data.value
                    _state.update { it.copy(cityList = data.value) }
                    if (latitude != null && longitude != null) {
                        getEstimatedCity(latitude!!, longitude!!)
                    }
                }
            }
            _state.update { it.copy(isLoading = false) }
        }
    }

    private fun getEstimatedCity(lat: Double, lon: Double) {
        latitude = lat
        longitude = lon
        var city = state.value.cityEstimated
        var minDistance = Double.MAX_VALUE

        cities.forEach {
            val distance = Coordinates.distance(
                lat1 = lat,
                lon1 = lon,
                lat2 = it.coordinates.lon,
                lon2 = it.coordinates.lat
            )
            if (distance < minDistance) {
                minDistance = distance
                city = it
            }
        }
        _state.update { it.copy(cityEstimated = city) }
    }

    private fun saveCity() {

    }

    private fun filterCityList(textSearch: String) {
        val list = cities.filter { it.name.contains(textSearch, ignoreCase = true) }
        _state.update { it.copy(cityList = list, textSearch = textSearch) }

        scope.launch {
            _effect.emit(Effect.ShowToast(textSearch))
        }
    }
}