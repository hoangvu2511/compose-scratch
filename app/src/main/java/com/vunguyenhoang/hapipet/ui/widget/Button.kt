package com.vunguyenhoang.hapipet.ui.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true
) {
    var backgroundColor = Color.White
    var textColor = colorResource(id = R.color.pink)
    var borderColor = backgroundColor
    if (isTextWhite) {
        backgroundColor = textColor
        textColor = Color.White
        borderColor = textColor
    }
    if (isEnabled) {
        borderColor = colorResource(id = R.color.grayScale)
    }

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor
        ),
        border = BorderStroke(
            width = 1.dp,
            color = borderColor
        ),
        modifier = modifier,
        enabled = isEnabled
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
private fun LoadButton() {
    ButtonWithTextAndBackgroundOpposite(
        onClick = {},
        title = "Test",
        isTextWhite = false
    )
}


@Composable
fun SocialButton(
    icon: ImageVector? = null,
    modifier: Modifier = Modifier.fillMaxWidth(),
    backgroundColor: Color,
    btnTitle: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick, colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor
        ),
        modifier = modifier
    ) {
        icon?.let {
            Image(imageVector = it, contentDescription = null)
        }
        Text(text = btnTitle, style = MaterialTheme.typography.body1.copy(color = Color.White))
    }
}