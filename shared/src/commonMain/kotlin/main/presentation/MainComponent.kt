package main.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import tab_home.presentation.TabHomeComponent
import kotlinx.serialization.Serializable

class MainComponent(
    componentContext: ComponentContext,
    private val onNavigateToCheckout: () -> Unit
) : ComponentContext by componentContext {

    private val navigation = StackNavigation<Configuration>()

    val childStack = childStack(
        source = navigation,
        serializer = Configuration.serializer(),
        initialConfiguration = Configuration.TabHome,
        handleBackButton = true,
        childFactory = ::createChild
    )

    private fun createChild(
        configuration: Configuration,
        context: ComponentContext
    ): Child {
        return when (configuration) {
            is Configuration.TabHome -> Child.TabHome(
                TabHomeComponent(
                    componentContext = context
                )
            )

            is Configuration.TabCatalog -> Child.TabCatalog(
                TabCatalogComponent(
                    componentContext = context
                )
            )

            is Configuration.TabCart -> Child.TabCart(
                TabCartComponent(
                    componentContext = context,
                    onNavigateToCheckout = onNavigateToCheckout
                )
            )

            is Configuration.TabBonus -> Child.TabBonus(
                TabBonusComponent(
                    componentContext = context
                )
            )

            is Configuration.TabProfile -> Child.TabProfile(
                TabProfileComponent(
                    componentContext = context
                )
            )
        }
    }

    fun onTabHomeClick() {
        navigation.bringToFront(Configuration.TabHome)
    }

    fun onTabCatalogClick() {
        navigation.bringToFront(Configuration.TabCatalog)
    }

    fun onTabCartClick() {
        navigation.bringToFront(Configuration.TabCart)
    }

    fun onTabBonusClick() {
        navigation.bringToFront(Configuration.TabBonus)
    }

    fun onTabProfileClick() {
        navigation.bringToFront(Configuration.TabProfile)
    }

    sealed class Child {
        data class TabHome(val component: TabHomeComponent) : Child()
        data class TabCatalog(val component: TabCatalogComponent) : Child()
        data class TabCart(val component: TabCartComponent) : Child()
        data class TabBonus(val component: TabBonusComponent) : Child()
        data class TabProfile(val component: TabProfileComponent) : Child()
    }

    @Serializable
    sealed class Configuration {
        @Serializable
        data object TabHome : Configuration()

        @Serializable
        data object TabCatalog : Configuration()

        @Serializable
        data object TabCart : Configuration()

        @Serializable
        data object TabBonus : Configuration()

        @Serializable
        data object TabProfile : Configuration()
    }
}