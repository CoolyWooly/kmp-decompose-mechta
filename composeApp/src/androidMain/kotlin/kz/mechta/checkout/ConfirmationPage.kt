package kz.mechta.checkout

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import checkout.ConfirmationComponent
import checkout.Step1Component

@Composable
fun ConfirmationPage(component: ConfirmationComponent) {
    Surface {
        Column {
            Text(text = "ConfirmationPage")
        }
    }
}