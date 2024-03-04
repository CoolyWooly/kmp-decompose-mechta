package kz.mechta.main

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import main.presentation.TabBonusComponent

@Composable
fun TabBonusPage(
    component: TabBonusComponent
) {
    Surface {
        Column {
            Text(text = "TabBonusPage")
        }
    }
}