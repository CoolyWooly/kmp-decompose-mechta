package kz.mechta.main

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import kz.mechta.shared.MR
import main.presentation.TabCatalogComponent

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