package kz.mechta.main

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import components.main.TabCartComponent
import components.main.TabCartEvent
import components.on_boarding.OnBoardingEvent

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