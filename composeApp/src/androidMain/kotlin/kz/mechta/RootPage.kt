package kz.mechta

import components.RootComponent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import kz.mechta.checkout.CheckoutPage
import kz.mechta.city_select.CitySelectPage
import kz.mechta.main.MainPage
import kz.mechta.onboarding.OnBoardingPage
import kz.mechta.theme.MechtaTheme

@Composable
fun RootPage(root: RootComponent) {
    MechtaTheme {
        val childStack by root.childStack.subscribeAsState()
        Children(
            stack = childStack,
            animation = stackAnimation(slide())
        ) { child ->
            when(val instance = child.instance) {
                is RootComponent.Child.Checkout -> CheckoutPage(instance.component)
                is RootComponent.Child.Main -> MainPage(instance.component)
                is RootComponent.Child.OnBoarding -> OnBoardingPage(instance.component)
                is RootComponent.Child.CitySelect -> CitySelectPage(instance.component)
            }
        }
    }
}