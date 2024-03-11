package tab_home.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryModel(
    @SerialName("code") val code: String,
    @SerialName("name") val name: String,
    @SerialName("section_image") val imageUrl: String
)