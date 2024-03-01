package components.on_boarding

import models.CityModel
import models.CoordinateModel


data class OnBoardingState(
    val selectedIndex: Int = 0,
    val city: CityModel = CityModel(
        name = "Астана",
        code = "s1",
        coordinates = CoordinateModel(lat = 71.483008, lon = 51.152961610029)
    ),
)

sealed interface OnBoardingEffect {
    data class ShowToast(val text: String?) : OnBoardingEffect
}

sealed interface OnBoardingEvent {
    data class OnSwipe(val index: Int): OnBoardingEvent
    data object OnNextClick: OnBoardingEvent
    data object OnSelectCityClick: OnBoardingEvent
    data class OnCoordinatesFetch(val lat: Double, val lon: Double): OnBoardingEvent
}