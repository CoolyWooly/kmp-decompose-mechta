package checkout

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import kotlinx.serialization.Serializable
import main.TabBonusComponent
import main.TabCartComponent
import main.TabCatalogComponent
import main.TabHomeComponent

class CheckoutComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext {

    private val navigation = StackNavigation<Configuration>()

    val childStack = childStack(
        source = navigation,
        serializer = Configuration.serializer(),
        initialConfiguration = Configuration.Confirmation,
        handleBackButton = true,
        childFactory = ::createChild
    )

    private fun createChild(
        configuration: Configuration,
        context: ComponentContext
    ): Child {
        return when (configuration) {
            is Configuration.Step1 -> Child.Step1(
                Step1Component(
                    componentContext = context
                )
            )

            is Configuration.Step2 -> Child.Step2(
                Step2Component(
                    componentContext = context
                )
            )

            is Configuration.Step3 -> Child.Step3(
                Step3Component(
                    componentContext = context
                )
            )

            is Configuration.Confirmation -> Child.Confirmation(
                ConfirmationComponent(
                    componentContext = context
                )
            )
        }
    }

    fun onStep1Click() {
        navigation.bringToFront(Configuration.Step1)
    }

    fun onStep2Click() {
        navigation.bringToFront(Configuration.Step2)
    }

    fun onStep3Click() {
        navigation.bringToFront(Configuration.Step3)
    }

    fun onConfirmationClick() {
        navigation.bringToFront(Configuration.Confirmation)
    }

    sealed class Child {
        data class Step1(val component: Step1Component) : Child()
        data class Step2(val component: Step2Component) : Child()
        data class Step3(val component: Step3Component) : Child()
        data class Confirmation(val component: ConfirmationComponent) : Child()
    }

    @Serializable
    sealed class Configuration {
        @Serializable
        data object Step1 : Configuration()

        @Serializable
        data object Step2 : Configuration()

        @Serializable
        data object Step3 : Configuration()

        @Serializable
        data object Confirmation : Configuration()
    }
}