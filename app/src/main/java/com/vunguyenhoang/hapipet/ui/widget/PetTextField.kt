package com.vunguyenhoang.hapipet.ui.widget

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PetTextField(
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,
    placeHolder: @Composable () -> Unit = {},
    label: @Composable () -> Unit = {}
) {
    val (passwordVisible, setVisible) = rememberSaveable { mutableStateOf(false) }
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        shape = MaterialTheme.shapes.large.copy(CornerSize(8.dp)),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        visualTransformation = if (keyboardType == KeyboardType.Password && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = {
            if (keyboardType == KeyboardType.Password) {
                val image = if (passwordVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                IconButton(onClick = { setVisible(!passwordVisible) }) {
                    Icon(imageVector = image, null)
                }
            }
        },
        placeholder = placeHolder,
        label = label
    )
}


@Composable
fun PetSingleLengthTextField(
    char: String = "",
    updateChar: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    TextField(
        value = char,
        onValueChange = {
            updateChar(it)
        },
        singleLine = true,
        modifier = modifier.padding(12.dp),
        shape = MaterialTheme.shapes.small,
        colors = TextFieldDefaults.textFieldColors(
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Preview
@Composable
private fun LoadTextField() {
    Row {
        PetTextField("", {})
    }
}