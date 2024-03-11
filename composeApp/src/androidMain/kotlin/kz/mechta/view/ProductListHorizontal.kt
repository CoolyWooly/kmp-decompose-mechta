package kz.mechta.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.domain.ProductModel
import kz.mechta.theme.MechtaTheme
import kz.mechta.utils.extension.shimmerPlaceholder

@Composable
fun ProductsListHorizontal(
    isLoadingPrice: Boolean,
    productsList: List<ProductModel>,
    cartItemsList: List<Int>,
    favouriteItemsList: List<Int>,
    onFavouriteClick: (ProductModel) -> Unit,
    onProductItemClick: (ProductModel) -> Unit,
    onAddToCartClick: (ProductModel) -> Unit,
    onPreorderClick: (ProductModel) -> Unit,
    onOpenCartClick: () -> Unit,
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
    ) {
        items(productsList, key = { it.id }) {
            val isInCart by remember(cartItemsList) {
                derivedStateOf {
                    cartItemsList.contains(it.id)
                }
            }
            val isFavourite by remember(favouriteItemsList) {
                derivedStateOf {
                    favouriteItemsList.contains(it.id)
                }
            }
            ProductItem(
                modifier = Modifier.width(150.dp),
                photos = it.photos,
                name = it.name,
                isLoadingPrice = isLoadingPrice,
                bonus = it.bonus,
                chips = it.chips,
                priceDiscount = it.prices.discountedPrice,
                priceBase = it.prices.basePrice,
                isInCart = isInCart,
                isFavourite = isFavourite,
                isOnPreorder = it.preorder,
                onFavouriteClick = { onFavouriteClick(it) },
                onItemClick = { onProductItemClick(it) },
                onAddToCartClick = { onAddToCartClick(it) },
                onPreorderClick = { onPreorderClick(it) },
                onOpenCartClick = onOpenCartClick
            )
        }
    }
}

@Composable
fun TitledProductsListHorizontal(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    isLoadingPrice: Boolean,
    title: String,
    productsList: List<ProductModel>,
    cartItemsList: List<Int>,
    favouriteItemsList: List<Int>,
    onFavouriteClick: (ProductModel) -> Unit,
    onProductItemClick: (ProductModel) -> Unit,
    onAddToCartClick: (ProductModel) -> Unit,
    onPreorderClick: (ProductModel) -> Unit,
    onOpenCartClick: () -> Unit,
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = title,
            style = MechtaTheme.typography.h2,
            color = MechtaTheme.colors.text01,
        )
        Spacer(modifier = Modifier.size(24.dp))
        if (isLoading) {
            ProductsListHorizontalPlaceholder()
        } else {
            ProductsListHorizontal(
                isLoadingPrice = isLoadingPrice,
                productsList = productsList,
                cartItemsList = cartItemsList,
                favouriteItemsList = favouriteItemsList,
                onFavouriteClick = onFavouriteClick,
                onProductItemClick = onProductItemClick,
                onAddToCartClick = onAddToCartClick,
                onPreorderClick = onPreorderClick,
                onOpenCartClick = onOpenCartClick,
            )
        }
    }
}

@Composable
private fun ProductsListHorizontalPlaceholder() {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
    ) {
        items(5) {
            Box(modifier = Modifier
                .width(150.dp)
                .height(250.dp)
                .shimmerPlaceholder(true))
        }
    }
}