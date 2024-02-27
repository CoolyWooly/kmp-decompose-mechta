package components.on_boarding

import models.CityModel
import models.CoordinateModel


data class State(
    val selectedIndex: Int = 0,
    val city: CityModel = CityModel(
        name = "Астана",
        code = "s1",
        coordinates = CoordinateModel(lat = 71.483008, lon = 51.152961610029)
    ),
)

sealed interface Effect {
    data class ShowToast(val text: String?) : Effect
}

sealed interface Event {
    data class OnSwipe(val index: Int): Event
    data object OnNextClick: Event
    data object OnSelectCityClick: Event
    data class OnCoordinatesFetch(val lat: Double, val lon: Double): Event
}