package components.tab_home


data class Model(
    val selectedIndex: Int = 0,
    val cityName: String = "",
    val trackLocation: Boolean = false,
)

sealed interface Effect {
    data object GetCoordinates : Effect
}

sealed interface Event {
    data class OnSwipe(val index: Int): Event
    data object OnNextClick: Event
    data object OnSelectCityClick: Event
    data class OnCoordinatesFetch(val lat: Double, val lon: Double): Event
}