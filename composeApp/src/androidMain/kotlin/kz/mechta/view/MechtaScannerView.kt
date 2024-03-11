package kz.mechta.view

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kz.mechta.R
import kz.mechta.theme.MechtaTheme

@Composable
fun MechtaScannerView(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(50),
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = MechtaTheme.colors.ui02),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Icon(
            modifier = Modifier.padding(12.dp),
            painter = painterResource(id = R.drawable.ic_scan),
            contentDescription = null,
            tint = Color.Unspecified
        )
    }
}