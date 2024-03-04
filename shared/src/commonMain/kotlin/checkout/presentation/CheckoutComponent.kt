package checkout.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import kotlinx.serialization.Serializable
import checkout.data.ProductRepository
import checkout.data.UserRepository
import core.domain.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CheckoutComponent(
    componentContext: ComponentContext,
) : ComponentContext by componentContext, KoinComponent {

    private val navigation = StackNavigation<Configuration>()
    private val productRepository by inject<ProductRepository>()
    private val userRepository by inject<UserRepository>()

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

        val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

//        val scope = componentCoroutineScope()
//
        scope.launch {
            when (val data = productRepository.getProduct("123")) {
                is Result.Error -> {}
                is Result.Success -> {
                    println(data.value.id)
                }
            }
            when (val data = userRepository.getProduct("456")) {
                is Result.Error -> {}
                is Result.Success -> {
                    println(data.value.id)
                }
            }
        }
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