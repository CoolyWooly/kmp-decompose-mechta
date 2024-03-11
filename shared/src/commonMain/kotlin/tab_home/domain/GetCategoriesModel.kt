package tab_home.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class GetCategoryResponse(
    @SerialName("popular_categories") val categoryList: List<CategoryModel>,
)