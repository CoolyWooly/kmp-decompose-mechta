package kz.mechta.utils.extension

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.fornewid.placeholder.foundation.PlaceholderHighlight
import io.github.fornewid.placeholder.foundation.placeholder
import io.github.fornewid.placeholder.foundation.shimmer
import kz.mechta.theme.MechtaTheme

@Composable
fun Modifier.shimmerPlaceholder(visible: Boolean): Modifier {
    return this then Modifier.placeholder(
        visible = visible,
        color = MechtaTheme.colors.ui03,
        shape = RoundedCornerShape(8.dp),
        highlight = PlaceholderHighlight.shimmer(highlightColor = Color.White),
    )
}