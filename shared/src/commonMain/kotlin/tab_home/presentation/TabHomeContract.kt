package tab_home.presentation

import core.domain.ProductModel
import tab_home.domain.ActionModel
import tab_home.domain.BannerModel
import tab_home.domain.BrandModel
import tab_home.domain.CategoryModel


data class TabHomeState(
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

sealed interface TabHomeEffect {
    data object GetCoordinates : TabHomeEffect
    data class ShowToast(val text: String?) : TabHomeEffect
}

sealed interface TabHomeEvent {
    data class OnSwipe(val index: Int): TabHomeEvent
    data object OnNextClick: TabHomeEvent
    data object OnSelectCityClick: TabHomeEvent
    data class OnCoordinatesFetch(val lat: Double, val lon: Double): TabHomeEvent
}