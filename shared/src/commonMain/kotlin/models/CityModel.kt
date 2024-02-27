package models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CityModel(
    @SerialName("code") val code: String,
    @SerialName("name") val name: String,
    @SerialName("coordinates") val coordinates: CoordinateModel,
)