package kz.mechta.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import components.main.MainComponent

@Composable
fun MainPage(component: MainComponent) {
    Surface (
        modifier = Modifier.fillMaxSize(),
    ) {
        Column {
            Children(modifier = Modifier.weight(1f), component = component)
            BottomBar(modifier = Modifier.fillMaxWidth(), component = component)
        }
    }
}

@Composable
private fun Children(
    modifier: Modifier = Modifier,
    component: MainComponent
) {
    Children(
        modifier = modifier,
        stack = component.childStack,
    ) { child ->
        when(val instance = child.instance) {
            is MainComponent.Child.TabHome -> TabHomePage(component = instance.component)
            is MainComponent.Child.TabCatalog -> TabCatalogPage(component = instance.component)
            is MainComponent.Child.TabCart -> TabCartPage(component = instance.component)
            is MainComponent.Child.TabBonus -> TabBonusPage(component = instance.component)
            is MainComponent.Child.TabProfile -> TabProfilePage(component = instance.component)
        }
    }
}

@Composable
private fun BottomBar(
    modifier: Modifier = Modifier,
    component: MainComponent
) {
    val childStack by component.childStack.subscribeAsState()
    val activeComponent = childStack.active.instance

    BottomNavigation(modifier = modifier) {
        BottomNavigationItem(
            selected = activeComponent is MainComponent.Child.TabHome,
            onClick = component::onTabHomeClick,
            icon = {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Home",
                )
            },
        )
        BottomNavigationItem(
            selected = activeComponent is MainComponent.Child.TabCatalog,
            onClick = component::onTabCatalogClick,
            icon = {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Catalog",
                )
            },
        )
        BottomNavigationItem(
            selected = activeComponent is MainComponent.Child.TabCart,
            onClick = component::onTabCartClick,
            icon = {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Cart",
                )
            },
        )
        BottomNavigationItem(
            selected = activeComponent is MainComponent.Child.TabBonus,
            onClick = component::onTabBonusClick,
            icon = {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Bonus",
                )
            },
        )
        BottomNavigationItem(
            selected = activeComponent is MainComponent.Child.TabProfile,
            onClick = component::onTabProfileClick,
            icon = {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Profile",
                )
            },
        )
    }
}