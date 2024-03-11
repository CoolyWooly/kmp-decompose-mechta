package kz.mechta.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kz.mechta.R
import kz.mechta.theme.MechtaTheme

@Composable
fun BonusView(modifier: Modifier = Modifier, value: Int) {
    Row(
        modifier = modifier
            .background(
                shape = CircleShape,
                brush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFFF9F9F9),
                    Color(0xFFF7F7F7),
                    Color(0xFFF0DFE6),
                )
            ))
            .padding(horizontal = 4.dp, vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$value",
            style = MechtaTheme.typography.caption,
            color = MechtaTheme.colors.brand01
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_bonus),
            contentDescription = null,
            tint = MechtaTheme.colors.brand01
        )
    }
}
