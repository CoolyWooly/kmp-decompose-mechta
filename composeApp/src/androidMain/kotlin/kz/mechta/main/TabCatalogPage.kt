package kz.mechta.main

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import main.TabCatalogComponent

@Composable
fun TabCatalogPage(
    component: TabCatalogComponent
) {
    Surface {
        Column {
            Text(text = "TabCatalogPage")
        }
    }
}