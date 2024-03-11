package kz.mechta.tab_home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import kz.mechta.tab_home.view.BannersPager
import kz.mechta.tab_home.view.CategoriesPager
import kz.mechta.tab_home.view.ImInShopButton
import kz.mechta.theme.MechtaTheme
import kz.mechta.view.MechtaScannerView
import kz.mechta.view.MechtaSearchView
import kz.mechta.view.TitledProductsListHorizontal
import `mechta-kmp`.shared.MR
import tab_home.presentation.TabHomeComponent

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabHomePage(
    component: TabHomeComponent
) {
    val uiState by component.state.subscribeAsState()
    Surface(modifier = Modifier.fillMaxSize(), color = MechtaTheme.colors.ui02) {
        val listState = rememberLazyListState()
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = listState
        ) {
            item {
                BannersPager(
                    modifier = Modifier
                        .background(color = MechtaTheme.colors.ui01)
                        .padding(top = 16.dp),
                    isLoading = uiState.isLoadingBanners,
                    banners = uiState.bannersList,
                    onBannerClick = { _, _ -> },
                )
            }
            stickyHeader {
                val paddingSizeAnimation: Dp by animateDpAsState(
                    if (listState.firstVisibleItemIndex > 0) 16.dp else 24.dp, label = "",
                )
                val searchElevationAnimation: Dp by animateDpAsState(
                    if (listState.firstVisibleItemIndex > 0) 0.dp else 3.dp, label = "",
                )
                val qrSizeAnimation: Dp by animateDpAsState(
                    if (listState.firstVisibleItemIndex > 0) 48.dp else 0.dp, label = "",
                )
                val isPinned by remember {
                    derivedStateOf { listState.firstVisibleItemIndex > 0 }
                }
                Row(
                    modifier = Modifier
                        .background(color = MechtaTheme.colors.ui01)
                        .padding(horizontal = paddingSizeAnimation, vertical = 12.dp),
                ) {
                    MechtaSearchView(
                        modifier = Modifier.weight(1f),
                        backgroundColor = if (isPinned) MechtaTheme.colors.ui02 else MechtaTheme.colors.ui01,
                        elevation = searchElevationAnimation,
                        onClick = { },
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    MechtaScannerView(
                        modifier = Modifier.width(qrSizeAnimation),
                        onClick = { }
                    )
                }
            }
            item {
                CategoriesPager(
                    categoriesList = uiState.categoriesList,
                    onClick = { }
                )
                Spacer(modifier = Modifier.size(32.dp))
                ImInShopButton(
                    onClick = { },
                    onCloseClick = { }
                )
                Spacer(modifier = Modifier.size(32.dp))
                TitledProductsListHorizontal(
                    isLoading = uiState.recommendationPersonalList.isEmpty(),
                    isLoadingPrice = false,
                    title = stringResource(id = MR.strings.recommendations.resourceId),
                    productsList = uiState.recommendationPersonalList,
                    cartItemsList = listOf(1),
                    favouriteItemsList = listOf(1),
                    onFavouriteClick = {},
                    onProductItemClick = {},
                    onAddToCartClick = {},
                    onPreorderClick = {},
                    onOpenCartClick = {}
                )
                Spacer(modifier = Modifier.size(32.dp))
                TitledProductsListHorizontal(
                    isLoading = uiState.recommendationHitsList.isEmpty(),
                    isLoadingPrice = false,
                    title = stringResource(id = MR.strings.top_sales.resourceId),
                    productsList = uiState.recommendationHitsList,
                    cartItemsList = listOf(1),
                    favouriteItemsList = listOf(1),
                    onFavouriteClick = {},
                    onProductItemClick = {},
                    onAddToCartClick = {},
                    onPreorderClick = {},
                    onOpenCartClick = {}
                )
            }
            item {
                Box(modifier = Modifier.size(height = 1000.dp, width = 1.dp))
            }
        }
    }
}