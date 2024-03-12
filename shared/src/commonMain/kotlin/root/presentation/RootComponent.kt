package root.presentation

import checkout.presentation.CheckoutComponent
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.active
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.router.stack.replaceAll
import city_select.presentation.CitySelectComponent
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import main.presentation.MainComponent
import on_boarding.data.OnBoardingRepository
import on_boarding.presentation.OnBoardingComponent
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import root.data.RootRepository
import splashscreen.presentation.SplashscreenComponent
import tab_home.data.TabHomeRepository
import tab_home.presentation.TabHomeEffect
import tab_home.presentation.TabHomeEvent
import tab_home.presentation.TabHomeState
import utils.WrappedSharedFlow

class RootComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext, KoinComponent {

    private val rootRepository by inject<RootRepository>()
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private val _state = MutableValue(RootState())
    val state: Value<RootState> = _state
    private val _effect = MutableSharedFlow<RootEffect>()
    val effect: WrappedSharedFlow<RootEffect> = WrappedSharedFlow(_effect.asSharedFlow())

    private val navigation = StackNavigation<Configuration>()

    val childStack = childStack(
        source = navigation,
        serializer = Configuration.serializer(),
        initialConfiguration = Configuration.Splashscreen,
        handleBackButton = true,
        childFactory = ::createChild
    )

    init {

    }

    fun onEvent(event: RootEvent) {
        when (event) {
            is RootEvent.OnMindboxDeviceId -> setMindboxDeviceId(event.deviceId)
        }
    }

    private fun setMindboxDeviceId(deviceId: String) {
        scope.launch {
            rootRepository.setMindboxDeviceId(deviceId)
        }
    }

    private fun createChild(
        configuration: Configuration,
        context: ComponentContext
    ): Child {
        return when (configuration) {
            is Configuration.Splashscreen -> Child.Splashscreen(
                SplashscreenComponent(
                    componentContext = context,
                    onNavigateToMain = {
                        navigation.replaceAll(Configuration.Main)
                    },
                    onNavigateToOnBoarding = {
                        navigation.replaceAll(Configuration.OnBoarding)
                    }
                )
            )

            is Configuration.OnBoarding -> Child.OnBoarding(
                OnBoardingComponent(
                    componentContext = context,
                    onNavigateToMain = {
                        navigation.replaceAll(Configuration.Main)
                    },
                    onNavigateToCitySelect = {
                        navigation.push(Configuration.CitySelect)
                    }
                )
            )

            is Configuration.Main -> Child.Main(
                MainComponent(
                    componentContext = context,
                    onNavigateToCheckout = {
                        navigation.push(Configuration.Checkout)
                    }
                )
            )

            is Configuration.Checkout -> Child.Checkout(
                CheckoutComponent(
                    componentContext = context
                )
            )

            is Configuration.CitySelect -> Child.CitySelect(
                CitySelectComponent(
                    componentContext = context,
                    onSendResult = { cityModel ->
                        navigation.pop {
                            val component =
                                (childStack.active.instance as? Child.OnBoarding)?.component
                            component?.onCitySelected(cityModel)
                        }
                    },
                )
            )
        }
    }

    sealed class Child {
        data class Splashscreen(val component: SplashscreenComponent) : Child()
        data class OnBoarding(val component: OnBoardingComponent) : Child()
        data class Main(val component: MainComponent) : Child()
        data class Checkout(val component: CheckoutComponent) : Child()
        data class CitySelect(val component: CitySelectComponent) : Child()
    }

    @Serializable
    sealed class Configuration {

        @Serializable
        data object Splashscreen : Configuration()

        @Serializable
        data object OnBoarding : Configuration()

        @Serializable
        data object Main : Configuration()

        @Serializable
        data object Checkout : Configuration()

        @Serializable
        data object CitySelect : Configuration()
    }
}