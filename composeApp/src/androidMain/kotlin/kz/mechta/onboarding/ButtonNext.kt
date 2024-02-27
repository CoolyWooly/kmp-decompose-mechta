package kz.mechta.onboarding

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.progressSemantics
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import kz.mechta.R
import kz.mechta.theme.MechtaTheme
import `mechta-kmp`.shared.MR

@Composable
fun ButtonNext(
    selectedIndex: Int,
    onClick: () -> Unit
) {
    val indicatorProgress = when (selectedIndex) {
        0 -> 0.33f
        1 -> 0.66f
        2 -> 1f
        else -> 0.3f
    }

    var progress by remember { mutableFloatStateOf(0f) }
    val progressAnimation by animateFloatAsState(
        targetValue = indicatorProgress,
        animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing),
        label = ""
    )

    Box(
        modifier = Modifier
            .size(95.dp)
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(MechtaTheme.colors.brand01, Color.Transparent)
                )
            ),
        contentAlignment = Alignment.Center
    ) {

        CircularProgressIndicator(
            modifier = Modifier.size(85.dp),
            progress = { 1f },
            color = Color.White
        )
        CircularProgressIndicator(
            modifier = Modifier.size(85.dp),
            progress = { progressAnimation },
            strokeCap = StrokeCap.Round
        )

        LaunchedEffect(indicatorProgress) {
            progress = indicatorProgress
        }

        Button(
            onClick = onClick,
            modifier = Modifier.size(70.dp),
            shape = CircleShape
        ) {
            if (selectedIndex == 2) {
                Text(
                    text = stringResource(id = MR.strings.yes.resourceId),
                    style = MechtaTheme.typography.button,
                    color = Color.White
                )
            } else {
                Icon(
                    painter = painterResource(id = R.drawable.ic_onboarding_next),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    }
}