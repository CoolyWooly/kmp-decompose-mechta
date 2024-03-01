package kz.mechta.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.foundation.text2.input.TextFieldLineLimits
import androidx.compose.foundation.text2.input.rememberTextFieldState
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.error
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import kz.mechta.R
import kz.mechta.theme.MechtaTheme
import `mechta-kmp`.shared.MR

//@OptIn(ExperimentalFoundationApi::class)
//@Composable
//fun MechtaTextField(
//    modifier: Modifier = Modifier,
//    hint: String? = null,
//    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
//    keyboardActions: KeyboardActions = KeyboardActions.Default,
//    lineLimits: TextFieldLineLimits = TextFieldLineLimits.Default,
//    textStyle: TextStyle = TextStyle.Default,
//) {
//    val interactionSource = remember { MutableInteractionSource() }
//    val isFocused by interactionSource.collectIsFocusedAsState()
//    val text = rememberTextFieldState()
//    val borderColor = if (isFocused) {
//        MechtaTheme.colors.brand01
//    } else {
//        Color.Transparent
//    }
//    BasicTextField2(
//        modifier = modifier
//            .background(
//                shape = RoundedCornerShape(50),
//                color = MechtaTheme.colors.ui02
//            )
//            .border(
//                shape = RoundedCornerShape(50),
//                color = borderColor,
//                width = 1.dp
//            )
//            .padding(horizontal = 16.dp, vertical = 16.dp),
//        state = text,
//        interactionSource = interactionSource,
//        keyboardOptions = keyboardOptions,
//        keyboardActions = keyboardActions,
//        lineLimits = lineLimits,
//        textStyle = textStyle,
//        decorator = { innerTextField ->
//            Row(verticalAlignment = Alignment.CenterVertically) {
//                Box(modifier = Modifier.weight(1f)) {
//                    if (text.text.isEmpty() && !hint.isNullOrEmpty()) {
//                        Text(
//                            text = hint,
//                            style = MechtaTheme.typography.body2,
//                            color = MechtaTheme.colors.text02
//                        )
//                    }
//                    innerTextField()
//                }
//                Icon(painter = painterResource(id = R.drawable.ic_geo), contentDescription = null)
//            }
//        }
//    )
//}

