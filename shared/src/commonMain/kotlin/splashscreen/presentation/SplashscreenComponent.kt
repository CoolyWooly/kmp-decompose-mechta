package splashscreen.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import core.domain.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import city_select.domain.model.CityModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import on_boarding.data.OnBoardingRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import splashscreen.data.SplashscreenRepository
import utils.Coordinates
import utils.WrappedSharedFlow

interface IOnSplashscreenComponent {

}

class SplashscreenComponent(
    componentContext: ComponentContext,
    private val onNavigateToMain: () -> Unit,
    private val onNavigateToOnBoarding: () -> Unit,
) : IOnSplashscreenComponent, ComponentContext by componentContext, KoinComponent {

    private val splashscreenRepository by inject<SplashscreenRepository>()
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private val _state = MutableValue(SplashscreenState())
    val state: Value<SplashscreenState> = _state
    private val _effect = MutableSharedFlow<SplashscreenEffect>()
    val effect: WrappedSharedFlow<SplashscreenEffect> = WrappedSharedFlow(_effect.asSharedFlow())

    init {
        getCity { cityEmpty ->
            if (cityEmpty) {
                onNavigateToOnBoarding()
            } else {
                onNavigateToMain()
            }
        }
    }

    fun onEvent(event: SplashscreenEvent) {
        when (event) {
            is SplashscreenEvent.OnSwipe -> {}
        }
    }

    private fun getCity(
        decision: (cityEmpty: Boolean) -> Unit,
    ) {
        scope.launch {
            val city = splashscreenRepository.getCity().first()
            delay(3000)
            decision(city == null)
        }
    }
}