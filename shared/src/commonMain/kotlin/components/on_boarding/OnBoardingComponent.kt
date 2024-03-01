package components.on_boarding

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import data.repository.MapRepository
import extensions.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import models.CityModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import utils.Coordinates
import utils.WrappedSharedFlow

interface IOnBoardingComponent {

    fun onCitySelected(cityModel: CityModel)
}

class OnBoardingComponent(
    componentContext: ComponentContext,
    private val onNavigateToMain: () -> Unit,
    private val onNavigateToCitySelect: () -> Unit,
) : IOnBoardingComponent, ComponentContext by componentContext, KoinComponent {

    private val mapRepository by inject<MapRepository>()
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private val _state = MutableValue(OnBoardingState())
    val state: Value<OnBoardingState> = _state
    private val _effect = MutableSharedFlow<OnBoardingEffect>()
    val effect: WrappedSharedFlow<OnBoardingEffect> = WrappedSharedFlow(_effect.asSharedFlow())

    private var cities: List<CityModel> = emptyList()

    init {
        getCityList()
    }

    fun onEvent(event: OnBoardingEvent) {
        when (event) {
            is OnBoardingEvent.OnSwipe -> {
                _state.update { it.copy(selectedIndex = event.index) }
            }

            is OnBoardingEvent.OnNextClick -> {
                if (_state.value.selectedIndex == 2) {
                    onNavigateToMain()
                } else {
                    val nextIndex = _state.value.selectedIndex + 1
                    _state.update { it.copy(selectedIndex = nextIndex) }
                }
            }

            is OnBoardingEvent.OnCoordinatesFetch -> {
                getEstimatedCity(event.lat, event.lon)
            }

            is OnBoardingEvent.OnSelectCityClick -> {
                onNavigateToCitySelect()
            }
        }
    }

    private fun getCityList() {
        scope.launch {
            when (val data = mapRepository.getCities()) {
                is Result.Error -> {
                    _effect.emit(OnBoardingEffect.ShowToast(data.text))
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

    override fun onCitySelected(cityModel: CityModel) {
        scope.launch {
            mapRepository.setCity(cityModel)
            onNavigateToMain()
        }
    }
}