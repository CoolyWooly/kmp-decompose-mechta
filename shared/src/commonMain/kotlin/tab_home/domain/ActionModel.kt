package tab_home.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ActionModel(
    @SerialName("name") val name: String,
    @SerialName("code") val code: String,
    @SerialName("image_src") val imageSrc: String,
    @SerialName("preview_text") val previewText: String,
    @SerialName("active_from") val activeFrom: String,
    @SerialName("active_to") val activeTo: String,
    @SerialName("brand_logo") val brandLogo: String,
    @SerialName("days_before_expiration") val daysBeforeExpiration: Int,
)