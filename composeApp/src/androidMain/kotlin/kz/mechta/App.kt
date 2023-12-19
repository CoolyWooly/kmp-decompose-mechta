package kz.mechta

import RootComponent
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import kz.mechta.checkout.CheckoutPage
import kz.mechta.main.MainPage
import kz.mechta.onboarding.OnBoardingPage

@Composable
fun App(root: RootComponent) {
    MaterialTheme {
        val childStack by root.childStack.subscribeAsState()
        Children(
            stack = childStack,
            animation = stackAnimation(slide())
        ) { child ->
            when(val instance = child.instance) {
                is RootComponent.Child.Checkout -> CheckoutPage(instance.component)
                is RootComponent.Child.Main -> MainPage(instance.component)
                is RootComponent.Child.OnBoarding -> OnBoardingPage(instance.component)
            }
        }
    }
}