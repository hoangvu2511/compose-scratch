package com.vunguyenhoang.hapipet.ui.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RemoveDone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun HapiPetKeyBoard(
    onClickButton: (String) -> Unit = {}
) {

    val arr = (1..9).map { "$it" }.toMutableList()
    arr.addAll(arrayOf(" ", "0", "remove"))
    val res = arr.chunked(3)
    LazyColumn {
        items(items = res) { row ->
            Row(modifier = Modifier.fillMaxWidth()) {
                row.forEach {
                    KeyboardItem(
                        modifier = Modifier.weight(1f),
                        content = it,
                        onClick = onClickButton
                    )
                }
            }
        }
    }
}


@Preview
@Composable
private fun KeyboardItem(
    modifier: Modifier = Modifier,
    content: String = "",
    onClick: (String) -> Unit = {}
) {
    val textHeightSp = 16.sp
    val iconHeightDp: Dp = with(LocalDensity.current) {
        textHeightSp.toDp()
    }

    Surface(
        modifier = modifier.clickable { if (content.isNotEmpty()) onClick(content) },
        border = BorderStroke(1.dp, color = Color.LightGray),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (content == "remove") {
                Icon(
                    imageVector = Icons.Default.RemoveDone,
                    contentDescription = "",
                    modifier = Modifier.size(iconHeightDp)
                )
            } else {
                Text(text = content, fontSize = textHeightSp)
            }
        }
    }
}