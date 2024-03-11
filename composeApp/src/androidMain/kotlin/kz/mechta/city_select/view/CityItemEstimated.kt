package kz.mechta.city_select.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kz.mechta.R
import kz.mechta.theme.MechtaTheme
import `mechta-kmp`.shared.MR

@Composable
fun CityItemEstimated(name: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .clickable { onClick() }
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_geo),
            contentDescription = null,
            tint = MechtaTheme.colors.text01
        )
        Spacer(modifier = Modifier.size(12.dp))
        Column {
            Text(
                text = name,
                style = MechtaTheme.typography.body1,
                color = MechtaTheme.colors.text01,
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = stringResource(id = MR.strings.most_likely_you_here.resourceId),
                style = MechtaTheme.typography.subtitle2,
                color = MechtaTheme.colors.text02,
            )
        }
    }
}
