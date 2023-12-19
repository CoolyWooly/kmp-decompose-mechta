package main

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.update

class TabCartComponent (
    componentContext: ComponentContext,
    private val onNavigateToCheckout: () -> Unit
) : ComponentContext by componentContext{

    fun onEvent(event: TabCartEvent) {
        when(event) {
            is TabCartEvent.OnCheckoutClick -> {
                onNavigateToCheckout()
            }
        }
    }
}