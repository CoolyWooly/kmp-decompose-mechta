package kz.mechta.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import on_boarding.OnBoardingComponent
import on_boarding.OnBoardingEvent

@Composable
fun OnBoardingPage(component: OnBoardingComponent) {
    Surface (
        modifier = Modifier.fillMaxSize(),
        color = Color.Gray
    ) {
        Column {
            Button(
                modifier = Modifier.size(100.dp),
                onClick = { component.onEvent(OnBoardingEvent.OnButtonPress) }
            ) {
                Text(text = "qwer")
            }
        }
    }
}