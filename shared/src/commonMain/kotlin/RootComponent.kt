import checkout.CheckoutComponent
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.router.stack.replaceAll
import kotlinx.serialization.Serializable
import main.MainComponent
import on_boarding.OnBoardingComponent

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
                    }
                )
            )
        }
    }

    sealed class Child {
        data class OnBoarding(val component: OnBoardingComponent): Child()
        data class Main(val component: MainComponent): Child()
        data class Checkout(val component: CheckoutComponent): Child()
    }

    @Serializable
    sealed class Configuration {
        @Serializable
        data object OnBoarding: Configuration()
        @Serializable
        data object  Main : Configuration()
        @Serializable
        data object  Checkout : Configuration()
    }
}