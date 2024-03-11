package root.presentation

import core.domain.ProductModel
import tab_home.domain.ActionModel
import tab_home.domain.BannerModel
import tab_home.domain.BrandModel
import tab_home.domain.CategoryModel


data class RootState(
    val isLoadingBanners: Boolean = true,
    val isLoadingCategories: Boolean = true,
    val isLoadingActions: Boolean = true,
    val isLoadingNews: Boolean = true,
    val isLoadingSubscribe: Boolean = false,
    val isQrVisible: Boolean = true,
    val bannersList: List<BannerModel> = emptyList(),
    val categoriesList: List<CategoryModel> = emptyList(),
    val actionsList: List<ActionModel> = emptyList(),
    val newsList: List<ActionModel> = emptyList(),
    val brandsList: List<BrandModel> = emptyList(),
    val recommendationHitsList: List<ProductModel> = emptyList(),
    val recommendationPersonalList: List<ProductModel> = emptyList(),
    val historyProductList: List<ProductModel> = emptyList(),
    val email: String = "",
    val cartList: List<Int> = emptyList(),
    val favouriteList: List<Int> = emptyList(),
)

sealed interface RootEffect {
    data object GetCoordinates : RootEffect
    data class ShowToast(val text: String?) : RootEffect
}

sealed interface RootEvent {
    data class OnMindboxDeviceId(val deviceId: String): RootEvent
}