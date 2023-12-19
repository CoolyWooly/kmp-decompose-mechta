package kz.mechta.main

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import main.TabProfileComponent

@Composable
fun TabProfilePage(
    component: TabProfileComponent
) {
    Surface {
        Column {
            Text(text = "TabProfilePage")
        }
    }
}