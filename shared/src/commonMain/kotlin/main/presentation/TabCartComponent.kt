package main.presentation

import com.arkivanov.decompose.ComponentContext

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