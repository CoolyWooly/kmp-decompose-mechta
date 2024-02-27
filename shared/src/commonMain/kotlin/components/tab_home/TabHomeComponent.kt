package components.tab_home

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import utils.WrappedSharedFlow

class TabHomeComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext {

    private val _model = MutableValue(Model())
    val model: Value<Model> = _model
    private val _effect = MutableSharedFlow<Effect>()
    val effect: WrappedSharedFlow<Effect> = WrappedSharedFlow(_effect.asSharedFlow())
}