package kz.mechta.city_select.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kz.mechta.theme.MechtaTheme

@Composable
fun CityItem(name: String, onClick: () -> Unit) {
    Column {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(1.dp)
                .background(color = MechtaTheme.colors.ui03)
        )
        Text(
            modifier = Modifier
                .clickable { onClick() }
                .fillMaxWidth()
                .padding(16.dp),
            text = name,
            style = MechtaTheme.typography.body1,
            color = MechtaTheme.colors.text01
        )
    }
}