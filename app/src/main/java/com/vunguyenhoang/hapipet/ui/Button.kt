package com.vunguyenhoang.hapipet.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vunguyenhoang.hapipet.R

@Composable
fun ButtonWithTextAndBackgroundOpposite(
    onClick: () -> Unit,
    title: String,
    isTextWhite: Boolean = true,
    modifier: Modifier = Modifier
) {
    var backgroundColor = Color.White
    var textColor = colorResource(id = R.color.pink)
    if (isTextWhite) {
        backgroundColor = textColor
        textColor = Color.White
    }

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor
        ),
        border = BorderStroke(
            width = 1.dp,
            color = if (textColor != Color.White) textColor else backgroundColor
        ),
        modifier = modifier
    ) {
        Text(
            text = title, style = TextStyle(
                color = textColor
            )
        )
    }
}

@Preview
@Composable
fun LoadButton() {
    ButtonWithTextAndBackgroundOpposite(
        onClick = {},
        title = "Test",
        isTextWhite = false
    )
}