package kz.mechta.checkout

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import checkout.presentation.CheckoutComponent

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