package models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetCitiesResponse(
    @SerialName("cities") val cities: List<CityModel>
)