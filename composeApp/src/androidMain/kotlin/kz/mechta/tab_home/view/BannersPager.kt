package kz.mechta.tab_home.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import kz.mechta.utils.extension.shimmerPlaceholder
import kz.mechta.view.MechtaImageView
import kz.mechta.view.PagerIndicator
import tab_home.domain.BannerModel
import tab_home.domain.getBannerModelMock

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BannersPager(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    banners: List<BannerModel>,
    onBannerClick: (BannerModel, Int) -> Unit,
) {
    val newBanners = if (isLoading) {
        listOf(getBannerModelMock())
    } else {
        banners
    }
    val pagerState = rememberPagerState(pageCount = { newBanners.size })
    Box(contentAlignment = Alignment.BottomCenter) {
        HorizontalPager(
            modifier = modifier.fillMaxWidth(),
            state = pagerState,
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp),
            pageSpacing = 8.dp,
        ) { index ->
            BannerListItem(
                isLoading = isLoading,
                bannerModel = newBanners[index],
                onBannerClick = { onBannerClick(it, index) },
            )
        }
        PagerIndicator(
            modifier = Modifier.padding(8.dp),
            pagerState = pagerState,
        )
    }
}

@Composable
private fun BannerListItem(
    isLoading: Boolean,
    bannerModel: BannerModel,
    onBannerClick: (BannerModel) -> Unit,
) {
    MechtaImageView(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.334f, true)
            .clip(RoundedCornerShape(8.dp))
            .shimmerPlaceholder(isLoading)
            .clickable {
                if (bannerModel.url.isNotBlank()) {
                    onBannerClick(bannerModel)
                }
            },
        model = bannerModel.images.mobile,
        contentScale = ContentScale.FillBounds
    )
}