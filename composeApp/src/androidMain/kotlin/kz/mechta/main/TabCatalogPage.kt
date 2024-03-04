package kz.mechta.main

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import main.presentation.TabCatalogComponent
import `mechta-kmp`.shared.MR

@Composable
fun TabCatalogPage(
    component: TabCatalogComponent
) {
    Surface {
        Column {
            Text(text = stringResource(id = MR.strings.onboard_title_3.resourceId, "Fcasf"))
        }
    }
}