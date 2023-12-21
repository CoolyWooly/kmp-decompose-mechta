package kz.mechta.main

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import components.main.TabHomeComponent

@Composable
fun TabHomePage(
    component: TabHomeComponent
) {
    Surface {
        Column {
            Text(text = "tabhome")
        }
    }
}