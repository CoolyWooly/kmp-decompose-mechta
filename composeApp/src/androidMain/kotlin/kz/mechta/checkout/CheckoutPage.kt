package kz.mechta.checkout

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import components.checkout.CheckoutComponent
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import kz.mechta.main.TabBonusPage
import kz.mechta.main.TabCartPage
import kz.mechta.main.TabCatalogPage
import kz.mechta.main.TabHomePage
import kz.mechta.main.TabProfilePage
import components.main.MainComponent

@Composable
fun CheckoutPage(component: CheckoutComponent) {
    Surface (
        modifier = Modifier.fillMaxSize(),
    ) {
        Children(
            modifier = Modifier.fillMaxSize(),
            stack = component.childStack,
        ) { child ->
            when(val instance = child.instance) {
                is CheckoutComponent.Child.Confirmation -> ConfirmationPage(instance.component)
                is CheckoutComponent.Child.Step1 -> Step1Page(instance.component)
                is CheckoutComponent.Child.Step2 -> Step2Page(instance.component)
                is CheckoutComponent.Child.Step3 -> Step3Page(instance.component)
            }
        }
    }
}