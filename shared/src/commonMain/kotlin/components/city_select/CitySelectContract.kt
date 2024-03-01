package components.city_select

import models.CityModel
import models.CoordinateModel


data class State(
    val isLoading: Boolean = false,
    val cityList: List<CityModel> = emptyList(),
    val cityEstimated: CityModel = CityModel(
        name = "Астана",
        code = "s1",
        coordinates = CoordinateModel(lat = 71.483008, lon = 51.152961610029)
    ),
    val textSearch: String = ""
)

sealed interface Effect {
    data class ShowToast(val text: String?) : Effect
}

sealed interface Event {
    data class OnTextSearchChange(val text: String): Event
    data class OnCityClick(val city: CityModel): Event
    data class OnCoordinatesFetch(val lat: Double, val lon: Double): Event
}