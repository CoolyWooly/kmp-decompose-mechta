package kz.mechta.view

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kz.mechta.R
import kz.mechta.theme.MechtaTheme
import `mechta-kmp`.shared.MR

@Composable
fun MechtaSearchView(
    modifier: Modifier = Modifier,
    text: String = stringResource(MR.strings.i_search_.resourceId),
    backgroundColor: Color = MechtaTheme.colors.ui02,
    elevation: Dp = 0.dp,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(50),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        onClick = onClick,
        elevation = CardDefaults.cardElevation(defaultElevation = elevation)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = text,
                style = MechtaTheme.typography.body2,
                color = MechtaTheme.colors.text02
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "",
                tint = Color.Unspecified
            )
        }
    }
}

@Composable
fun MechtaSearchField(
    modifier: Modifier = Modifier,
    value: String,
    placeholderText: String,
    onValueChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    focusEnabled: Boolean = false
) {
    val focusRequester = remember { FocusRequester() }
    if (focusEnabled) {
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
    }
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
        value = value,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(50),
        trailingIcon = {
            if (value.isEmpty()) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "",
                    tint = Color.Unspecified
                )
            } else {
                IconButton(
                    onClick = { onValueChange("") }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_close),
                        contentDescription = "",
                        tint = Color.Unspecified
                    )
                }
            }
        },
        singleLine = true,
        placeholder = {
            Text(
                text = placeholderText,
                style = MechtaTheme.typography.body2,
                color = MechtaTheme.colors.text02
            )
        },
        colors = TextFieldDefaults.colors(
            cursorColor = MechtaTheme.colors.text01,
            focusedContainerColor = MechtaTheme.colors.ui02,
            unfocusedContainerColor = MechtaTheme.colors.ui02,
//            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
//            colors = TextFieldDefaults.textFieldColors(
//                textColor = MechtaTheme.colors.text01,
//                backgroundColor = MechtaTheme.colors.ui02,
//                cursorColor = Color.Black,
//                disabledLabelColor = Color.Gray,
//                focusedIndicatorColor = Color.Transparent,
//                unfocusedIndicatorColor = Color.Transparent
//            ),
        textStyle = MechtaTheme.typography.body2,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = { onSearchClick() }
        )
    )
}