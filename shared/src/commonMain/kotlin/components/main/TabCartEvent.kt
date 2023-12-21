package components.main

sealed interface TabCartEvent {
    data object OnCheckoutClick: TabCartEvent
}