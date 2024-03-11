package kz.mechta.view

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.SubcomposeAsyncImage
import kz.mechta.utils.extension.shimmerPlaceholder

@Composable
fun MechtaImageView(
    modifier: Modifier = Modifier,
    model: Any?,
    contentScale: ContentScale = ContentScale.Fit
) {
    SubcomposeAsyncImage(
        modifier = modifier,
        model = model,
        alignment = Alignment.Center,
        contentScale = contentScale,
        loading = {
            Box(modifier = Modifier.shimmerPlaceholder(true))
        },
        contentDescription = null
    )
}