package components.on_boarding

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update

class OnBoardingComponent(
    componentContext: ComponentContext,
    private val onNavigateToMain: () -> Unit
) : ComponentContext by componentContext {

    data class Model(
        val selectedIndex: Int = 0
    )

    private val _model =
        MutableValue(
            Model()
        )
    val model : Value<Model> = _model

    fun onEvent(event: OnBoardingEvent) {
        when(event) {
            is OnBoardingEvent.OnIndexChange -> {
                _model.update {
                    it.copy(
                        selectedIndex = event.index
                    )
                }
            }

            is OnBoardingEvent.OnButtonPress -> {
                onNavigateToMain()
            }
        }
    }
}