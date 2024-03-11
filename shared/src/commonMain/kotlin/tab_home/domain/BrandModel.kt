package tab_home.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BrandModel(
    @SerialName("img") val img: String,
    @SerialName("name") val name: String,
    @SerialName("code") val code: String,
)