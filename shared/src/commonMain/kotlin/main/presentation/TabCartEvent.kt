package main.presentation

sealed interface TabCartEvent {
    data object OnCheckoutClick: TabCartEvent
}