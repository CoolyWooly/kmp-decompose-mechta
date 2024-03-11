package core.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductModel(
    @SerialName("id") val id: Int,
    @SerialName("xml_id") val xmlId: String,
    @SerialName("name") val name: String,
    @SerialName("title") val title: String,
    @SerialName("code") val code: String,
    @SerialName("photos") val photos: List<String>,
    @SerialName("code_1c") val code1c: String,
    @SerialName("rating") val rating: Float,
    @SerialName("reviews_count") val reviewsCount: Int,
    @SerialName("preorder") val preorder: Boolean,
//    @SerialName("stickers") val stickers: Map<String, StickerModel>,
    @SerialName("metrics") val metrics: MetricsModel,
    @SerialName("breadcrumbs") val breadcrumbs: List<BreadcrumbModel> = emptyList(),
    @SerialName("main_properties") val mainProperties: List<MainPropertyModel> = emptyList(),
    @SerialName("availability") val availability: String,
    @SerialName("shops") val shops: List<ShopModel> = emptyList(),
    @SerialName("properties") val properties: Map<String, PropertyModel> = emptyMap(),
    @SerialName("same_products") val sameProducts: Map<String, SameProductCategoryModel> = emptyMap(),
    @SerialName("bonus") var bonus: Int = 0,
    @SerialName("chips") var chips: Int = 0,
    @SerialName("prices") var prices: PricesModel,
) {

    @Serializable
    data class StickerModel(
        @SerialName("name") val name: String,
        @SerialName("link") val link: String?,
        @SerialName("color") val color: String,
    )

    @Serializable
    data class MetricsModel(
        @SerialName("name") val name: String,
        @SerialName("brand") val brand: String,
        @SerialName("category") val category: String
    )

    @Serializable
    data class BreadcrumbModel(
        @SerialName("name") val name: String,
        @SerialName("code") val code: String,
    )

    @Serializable
    data class ShopModel(
        @SerialName("shop_name") val shopName: String,
        @SerialName("work_time") val workTime: String,
        @SerialName("work_time_short") val workTimeShort: String,
        @SerialName("coordinates") val coordinates: Coordinates?,
        @SerialName("items_count") val itemsCount: Double,
    ) {
        val isLastItem: Boolean
            get() = itemsCount == 0.1
    }

    @Serializable
    data class Coordinates(
        @SerialName("latitude") val latitude: Double,
        @SerialName("longitude") val longitude: Double,
    )

    @Serializable
    data class MainPropertyModel(
        @SerialName("prop_name") val propName: String?,
        @SerialName("prop_value") val propValue: String?,
    )

    @Serializable
    data class PropertyModel(
        @SerialName("prop_group_name") val propGroupName: String,
        @SerialName("prop_group_sort") val propGroupSort: Int,
        @SerialName("values") val values: List<PropertyValueModel>,
    )

    @Serializable
    data class PropertyValueModel(
        @SerialName("prop_name") val propName: String,
        @SerialName("prop_value") val propValue: String,
    )

    @Serializable
    data class SameProductCategoryModel(
        @SerialName("items") val items: Map<String, SameProductModel>,
        @SerialName("name") val name: String,
    )

    @Serializable
    data class SameProductModel(
        @SerialName("code") val code: String,
        @SerialName("value") val value: String,
        @SerialName("checked") val checked: Boolean,
        @SerialName("hex_code") val hexCode: String?,
    )

    @Serializable
    data class PricesModel(
        @SerialName("discounted_price") val discountedPrice: Int,
        @SerialName("base_price") val basePrice: Int,
    )

    fun get1cCode(): String {
        if (code1c.isBlank()) return ""
        return if (code1c.length < 5) {
            code1c
        } else {
            code1c.takeLast(5)
        }
    }

    companion object {
        const val CITY_EXIST = "city_exist"
        const val ON_VITRINE = "on_vitrine"
        const val IS_INTERCITY = "is_intercity"
        const val POD_ORDER_ITEM = "pod_order_item"
        const val PREORDER = "preorder"
        const val NOT_EXIST_IN_CITY = "not_exist_in_city"
    }
}