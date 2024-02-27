package kz.mechta.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import components.main.MainComponent

@Composable
fun MainPage(component: MainComponent) {
    Surface (
        modifier = Modifier.fillMaxSize(),
    ) {
        Column {
            Child(modifier = Modifier.weight(1f), component = component)
            BottomBar(modifier = Modifier.fillMaxWidth(), component = component)
        }
    }
}

@Composable
private fun Child(
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

    NavigationBar(modifier = modifier) {
        NavigationBarItem(
            selected = activeComponent is MainComponent.Child.TabHome,
            onClick = component::onTabHomeClick,
            icon = {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Home",
                )
            },
        )
        NavigationBarItem(
            selected = activeComponent is MainComponent.Child.TabCatalog,
            onClick = component::onTabCatalogClick,
            icon = {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Catalog",
                )
            },
        )
        NavigationBarItem(
            selected = activeComponent is MainComponent.Child.TabCart,
            onClick = component::onTabCartClick,
            icon = {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Cart",
                )
            },
        )
        NavigationBarItem(
            selected = activeComponent is MainComponent.Child.TabBonus,
            onClick = component::onTabBonusClick,
            icon = {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Bonus",
                )
            },
        )
        NavigationBarItem(
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