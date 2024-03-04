package city_select.domain.model

import city_select.domain.model.CityModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetCitiesResponse(
    @SerialName("cities") val cities: List<CityModel>
)