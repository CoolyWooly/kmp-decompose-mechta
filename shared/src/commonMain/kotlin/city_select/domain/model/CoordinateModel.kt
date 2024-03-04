package city_select.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoordinateModel(
    @SerialName("lat") val lat: Double,
    @SerialName("lon") val lon: Double,
)