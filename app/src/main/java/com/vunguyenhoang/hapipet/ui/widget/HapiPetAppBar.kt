package com.vunguyenhoang.hapipet.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun HapiPetAppBar(
    modifier: Modifier = Modifier,
    backBtn: Boolean = true,
    onBackBtnClick: () -> Unit = {},
    title: String = "Title"
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                color = Color(0xFF111111),
                fontSize = 18.sp
            )
        },
        elevation = 0.dp,
        modifier = modifier,
        backgroundColor = Color.Transparent,
        contentColor = Color.Transparent,
        navigationIcon = {
            if (backBtn) {
                val roundedShape = RoundedCornerShape(50)
                IconButton(
                    onClick = onBackBtnClick,
                    modifier = Modifier
                        .background(
                            color = Color.White,
                            shape = roundedShape
                        )
                        .border(
                            width = 1.dp,
                            color = Color.LightGray,
                            shape = roundedShape
                        )
                ) {
                    Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = "")
                }
            }
        }
    )
}