package kz.mechta.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kz.mechta.R
import kz.mechta.shared.MR
import kz.mechta.theme.MechtaTheme
import kz.mechta.utils.PictureUrlConverter
import kz.mechta.utils.extension.addEmptyLines
import kz.mechta.utils.extension.shimmerPlaceholder
import kz.mechta.utils.extension.spacedString

@Composable
fun ProductItem(
    modifier: Modifier = Modifier,
    photos: List<String>,
    name: String,
    isLoadingPrice: Boolean,
    bonus: Int,
    chips: Int,
    priceDiscount: Int,
    priceBase: Int,
    isInCart: Boolean,
    isFavourite: Boolean,
    isOnPreorder: Boolean,
    onFavouriteClick: () -> Unit,
    onItemClick: () -> Unit,
    onAddToCartClick: () -> Unit,
    onPreorderClick: () -> Unit,
    onOpenCartClick: () -> Unit,
) {
    Card(
        modifier = modifier,
        onClick = onItemClick,
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        colors = CardDefaults.cardColors(containerColor = MechtaTheme.colors.ui01)
    ) {
        Column {
            Picture(
                photos = photos,
                bonus = bonus,
                chips = chips,
                isFavourite = isFavourite,
                onFavouriteClick = onFavouriteClick,
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = name.addEmptyLines(2),
                style = MechtaTheme.typography.body2,
                color = MechtaTheme.colors.text01,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.size(4.dp))
            Prices(
                modifier = Modifier.padding(horizontal = 8.dp),
                isLoading = isLoadingPrice,
                priceDiscount = priceDiscount,
                priceBase = priceBase
            )
            Spacer(modifier = Modifier.size(12.dp))
            Buttons(
                modifier = Modifier.padding(horizontal = 8.dp),
                isInCart = isInCart,
                isOnPreorder = isOnPreorder,
                onPreorderClick = onPreorderClick,
                onAddToCartClick = onAddToCartClick,
                onOpenCartClick = onOpenCartClick
            )
            Spacer(modifier = Modifier.size(8.dp))
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Picture(
    photos: List<String>,
    bonus: Int,
    chips: Int,
    isFavourite: Boolean,
    onFavouriteClick: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .height(128.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val state = rememberPagerState(pageCount = { photos.size })
            val alpha = if (photos.size > 1) 1f else 0f
            HorizontalPager(
                modifier = Modifier
                    .aspectRatio(1f)
                    .padding(vertical = 4.dp),
                state = state,
                userScrollEnabled = photos.size > 1
            ) { index ->
                MechtaImageView(
                    modifier = Modifier.aspectRatio(1f),
                    model = PictureUrlConverter.getCompressed(
                        photos[index],
                        PictureUrlConverter.Size.SIZE_480
                    ),
                )
            }
            PagerIndicator(
                modifier = Modifier.alpha(alpha),
                pagerState = state,
            )
        }
        Column(modifier = Modifier.padding(vertical = 8.dp, horizontal = 4.dp)) {
            if (bonus > 0) {
                BonusView(value = bonus)
                Spacer(modifier = Modifier.size(4.dp))
            }
            if (chips > 0) {
                ChipView(value = chips)
            }
        }
        IconButton(
            modifier = Modifier.align(Alignment.TopEnd),
            onClick = onFavouriteClick
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_heart),
                contentDescription = null,
                tint = if (isFavourite) MechtaTheme.colors.brand01 else MechtaTheme.colors.ui05
            )
        }
    }
}

@Composable
private fun Prices(
    modifier: Modifier,
    isLoading: Boolean,
    priceDiscount: Int,
    priceBase: Int,
) {
    val priceSmall = if (priceDiscount > 0 && priceBase >0) {
        "${priceBase.spacedString()} ₸"
    } else {
        ""
    }
    val priceBig = if (priceDiscount >0 && priceBase > 0) {
        "${priceDiscount.spacedString()} ₸"
    } else if (priceDiscount == 0 && priceBase > 0) {
        "${priceBase.spacedString()} ₸"
    } else {
        ""
    }
    Column (modifier = modifier) {
        Text(
            modifier = Modifier.padding(horizontal = 8.dp),
            text = priceSmall,
            style = MechtaTheme.typography.oldPrice,
            color = MechtaTheme.colors.text02,
            maxLines = 1
        )
        Text(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth()
                .shimmerPlaceholder(visible = isLoading),
            text = priceBig,
            style = MechtaTheme.typography.h2,
            color = MechtaTheme.colors.text01,
            maxLines = 1
        )
    }
}

@Composable
private fun Buttons(
    modifier: Modifier,
    isInCart: Boolean,
    isOnPreorder: Boolean,
    onPreorderClick: () -> Unit,
    onAddToCartClick: () -> Unit,
    onOpenCartClick: () -> Unit,
) {
    val bgBtn: Color
    val btnText: Int
    val onBtnClick: () -> Unit

    if (isInCart) {
        bgBtn = MechtaTheme.colors.support02
        onBtnClick = onOpenCartClick
        btnText = MR.strings.in_basket.resourceId
    } else if (isOnPreorder) {
        bgBtn = MechtaTheme.colors.brand01
        onBtnClick = onPreorderClick
        btnText = MR.strings.preorder.resourceId
    } else {
        bgBtn = MechtaTheme.colors.brand01
        onBtnClick = onAddToCartClick
        btnText = MR.strings.to_basket.resourceId
    }

    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = onBtnClick,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = bgBtn)
    ) {
        Text(
            text = stringResource(btnText),
            style = MechtaTheme.typography.button,
            color = MechtaTheme.colors.ui01
        )
    }
}

