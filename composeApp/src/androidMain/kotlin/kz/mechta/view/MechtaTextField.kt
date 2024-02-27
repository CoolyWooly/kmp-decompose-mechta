package kz.mechta.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.foundation.text2.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kz.mechta.theme.MechtaTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MechtaTextField(
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val username = rememberTextFieldState()
    val borderColor = if (isFocused) {
        MechtaTheme.colors.brand01
    } else {
        Color.Transparent
    }
    BasicTextField2(
        modifier = modifier
            .background(
                shape = RoundedCornerShape(50),
                color = MechtaTheme.colors.ui02
            )
            .border(
                shape = RoundedCornerShape(50),
                color = borderColor,
                width = 1.dp
            )
            .padding(16.dp),
        state = username,
        interactionSource = interactionSource
    )
}