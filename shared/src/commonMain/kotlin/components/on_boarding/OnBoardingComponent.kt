package components.on_boarding

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import domain.MapRepository
import extensions.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import models.CityModel
import models.CoordinateModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import utils.Coordinates
import utils.WrappedSharedFlow

class OnBoardingComponent(
    componentContext: ComponentContext,
    private val onNavigateToMain: () -> Unit,
    private val onNavigateToCitySelect: () -> Unit,
) : ComponentContext by componentContext, KoinComponent {

    private val mapRepository by inject<MapRepository>()
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private val _state = MutableValue(State())
    val state: Value<State> = _state
    private val _effect = MutableSharedFlow<Effect>()
    val effect: WrappedSharedFlow<Effect> = WrappedSharedFlow(_effect.asSharedFlow())

    private var cities: List<CityModel> = emptyList()

    init {
        getCityList()
    }

    fun onEvent(event: Event) {
        when (event) {
            is Event.OnSwipe -> {
                _state.update { it.copy(selectedIndex = event.index) }
            }

            is Event.OnNextClick -> {
                if (_state.value.selectedIndex == 2) {
                    saveCity()
                    onNavigateToMain()
                } else {
                    val nextIndex = _state.value.selectedIndex + 1
                    _state.update { it.copy(selectedIndex = nextIndex) }
                }
            }

            is Event.OnCoordinatesFetch -> {
                getEstimatedCity(event.lat, event.lon)
            }

            is Event.OnSelectCityClick -> {
                onNavigateToCitySelect()
            }
        }
    }

    private fun getCityList() {
        scope.launch {
            when (val data = mapRepository.getCities()) {
                is Result.Error -> {
                    _effect.tryEmit(Effect.ShowToast(data.text))
                }

                is Result.Success -> {
                    cities = data.value
                }
            }
        }
    }

    private fun getEstimatedCity(lat: Double, lon: Double) {
        var city = state.value.city
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
        _state.update { it.copy(city = city) }
    }

    private fun saveCity() {

    }
}