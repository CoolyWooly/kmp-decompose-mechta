package on_boarding

sealed interface OnBoardingEvent {
    data class OnIndexChange(val index: Int): OnBoardingEvent
    data object OnButtonPress: OnBoardingEvent
}