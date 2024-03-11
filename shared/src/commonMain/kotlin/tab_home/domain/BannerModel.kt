package tab_home.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BannerModel(
    @SerialName("name") val name: String,
    @SerialName("url") val url: String,
    @SerialName("sort") val sort: Int,
    @SerialName("images") val images: Images
) {
    @Serializable
    data class Images(
        @SerialName("mobile") val mobile: String,
    )
}

fun getBannerModelMock(): BannerModel {
    return BannerModel("", "", 0, BannerModel.Images(""))
}
