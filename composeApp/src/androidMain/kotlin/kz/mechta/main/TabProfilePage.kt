package kz.mechta.main

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import main.presentation.TabProfileComponent

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