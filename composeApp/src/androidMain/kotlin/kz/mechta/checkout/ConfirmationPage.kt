package kz.mechta.checkout

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import checkout.presentation.ConfirmationComponent

@Composable
fun ConfirmationPage(component: ConfirmationComponent) {
    Surface {
        Column {
            Text(text = "ConfirmationPage")
        }
    }
}