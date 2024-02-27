package kz.mechta.main

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import components.main.TabCartComponent
import components.main.TabCartEvent

@Composable
fun TabCartPage(
    component: TabCartComponent
) {
    Surface {
        Column {
            Text(text = "TabCartPage")
            Button(
                onClick = { component.onEvent(TabCartEvent.OnCheckoutClick) }
            ) {
                Text(text = "to checkout")
            }
        }
    }
}