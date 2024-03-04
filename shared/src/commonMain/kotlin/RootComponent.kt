import checkout.presentation.CheckoutComponent
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.active
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.router.stack.replaceAll
import city_select.presentation.CitySelectComponent
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import main.presentation.MainComponent
import on_boarding.data.OnBoardingRepository
import on_boarding.presentation.OnBoardingComponent
import on_boarding.presentation.OnBoardingState
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RootComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext, KoinComponent {

    private val onBoardingRepository by inject<OnBoardingRepository>()
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private val navigation = StackNavigation<Configuration>()

    val childStack = childStack(
        source = navigation,
        serializer = Configuration.serializer(),
        initialConfiguration = Configuration.OnBoarding,
        handleBackButton = true,
        childFactory = ::createChild
    )

    init {
        scope.launch {
            val cityModel = onBoardingRepository.getCity().first()
            if (cityModel != null) {
                navigation.replaceAll(Configuration.Main)
            }
        }
    }

    private fun createChild(
        configuration: Configuration,
        context: ComponentContext
    ): Child {
        return when (configuration) {
            is Configuration.Checkout -> Child.Checkout(
                CheckoutComponent(
                    componentContext = context
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

            is Configuration.CitySelect -> Child.CitySelect(
                CitySelectComponent(
                    componentContext = context,
                    onSendResult = { cityModel ->
                        navigation.pop {
                            val component = (childStack.active.instance as? Child.OnBoarding)?.component
                            component?.onCitySelected(cityModel)
                        }
                    },
                )
            )
        }
    }

    sealed class Child {
        data class OnBoarding(val component: OnBoardingComponent) : Child()
        data class Main(val component: MainComponent) : Child()
        data class Checkout(val component: CheckoutComponent) : Child()
        data class CitySelect(val component: CitySelectComponent) : Child()
    }

    @Serializable
    sealed class Configuration {
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