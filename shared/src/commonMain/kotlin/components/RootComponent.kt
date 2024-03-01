package components

import components.checkout.CheckoutComponent
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.active
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.router.stack.replaceAll
import components.city_select.CitySelectComponent
import kotlinx.serialization.Serializable
import components.main.MainComponent
import components.on_boarding.OnBoardingComponent

class RootComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext {

    private val navigation = StackNavigation<Configuration>()

    val childStack = childStack(
        source = navigation,
        serializer = Configuration.serializer(),
        initialConfiguration = Configuration.OnBoarding,
        handleBackButton = true,
        childFactory = ::createChild
    )

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
                        navigation.pop { // Pop ItemDetailsComponent
                            // Deliver the result
                            (childStack.active.instance as? Child.OnBoarding)?.component?.onCitySelected(cityModel)
                        }
                    },
                )
            )
        }
    }

    sealed class Child {
        data class OnBoarding(val component: OnBoardingComponent): Child()
        data class Main(val component: MainComponent): Child()
        data class Checkout(val component: CheckoutComponent): Child()
        data class CitySelect(val component: CitySelectComponent): Child()
    }

    @Serializable
    sealed class Configuration {
        @Serializable
        data object OnBoarding: Configuration()
        @Serializable
        data object  Main : Configuration()
        @Serializable
        data object  Checkout : Configuration()
        @Serializable
        data object  CitySelect : Configuration()
    }
}