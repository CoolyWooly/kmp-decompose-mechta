package kz.mechta.tab_home.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kz.mechta.R
import kz.mechta.shared.MR
import kz.mechta.theme.MechtaTheme

@Composable
fun ImInShopButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onCloseClick: () -> Unit
) {
    Card(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        colors = CardDefaults.cardColors(containerColor = MechtaTheme.colors.ui01)
    ) {
        Box {
            Row(modifier = Modifier.padding(16.dp)) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = stringResource(id = MR.strings.im_in_shop.resourceId),
                        style = MechtaTheme.typography.h2,
                        color = MechtaTheme.colors.text01,
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    Text(
                        text = stringResource(id = MR.strings.scan_qr_and_find.resourceId),
                        style = MechtaTheme.typography.body1,
                        color = MechtaTheme.colors.text02,
                    )
                }
                Spacer(modifier = Modifier.size(12.dp))
                Image(
                    modifier = Modifier.size(92.dp),
                    painter = painterResource(id = R.drawable.img_qr_scan),
                    contentDescription = null
                )
            }

            IconButton(
                modifier = Modifier.align(Alignment.TopEnd),
                onClick = onCloseClick
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_close_rounded),
                    contentDescription = null
                )
            }
        }
    }
}