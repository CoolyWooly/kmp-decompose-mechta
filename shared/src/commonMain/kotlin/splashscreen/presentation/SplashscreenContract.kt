package splashscreen.presentation

data class SplashscreenState(
    val text: String = "",
)

sealed interface SplashscreenEffect {
    data class ShowToast(val text: String?) : SplashscreenEffect
}

sealed interface SplashscreenEvent {
    data class OnSwipe(val index: Int): SplashscreenEvent
}