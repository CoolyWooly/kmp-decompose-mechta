package kz.mechta

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import cloud.mindbox.mobile_sdk.Mindbox
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import kz.mechta.checkout.CheckoutPage
import kz.mechta.city_select.CitySelectPage
import kz.mechta.main.MainPage
import kz.mechta.onboarding.OnBoardingPage
import kz.mechta.splashscreen.SplashscreenPage
import kz.mechta.theme.MechtaTheme
import root.presentation.RootComponent
import root.presentation.RootEvent

@Composable
fun RootPage(root: RootComponent) {
    Mindbox.subscribeDeviceUuid {
        root.onEvent(RootEvent.OnMindboxDeviceId(it))
    }
    MechtaTheme {
        val childStack by root.childStack.subscribeAsState()
        Children(
            stack = childStack,
            animation = stackAnimation(slide())
        ) { child ->
            when(val instance = child.instance) {
                is RootComponent.Child.Splashscreen -> SplashscreenPage(instance.component)
                is RootComponent.Child.Checkout -> CheckoutPage(instance.component)
                is RootComponent.Child.Main -> MainPage(instance.component)
                is RootComponent.Child.OnBoarding -> OnBoardingPage(instance.component)
                is RootComponent.Child.CitySelect -> CitySelectPage(instance.component)
            }
        }
    }
}