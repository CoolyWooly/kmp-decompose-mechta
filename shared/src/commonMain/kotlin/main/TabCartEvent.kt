package main

sealed interface TabCartEvent {
    data object OnCheckoutClick: TabCartEvent
}