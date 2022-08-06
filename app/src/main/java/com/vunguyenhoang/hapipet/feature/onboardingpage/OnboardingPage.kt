package com.vunguyenhoang.hapipet.feature.onboardingpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.vunguyenhoang.hapipet.R
import com.vunguyenhoang.hapipet.ui.ButtonWithTextAndBackgroundOpposite
import com.vunguyenhoang.hapipet.ui.ScreenPath
import com.vunguyenhoang.hapipet.ui.theme.HapiPetTheme
import com.vunguyenhoang.hapipet.ui.theme.Typography
import kotlinx.coroutines.test.withTestContext

@Composable
fun OnBoardingPage(navController: NavController? = null) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val indexed = remember {
            mutableStateOf(0)
        }
        Image(painter = painterResource(id = R.drawable.ic_logo), contentDescription = null)
        LoadImageSlider(index = indexed, modifier = Modifier.weight(1f))
        BottomSheetOnBoardingPage(indexed, navController)
    }
}

@Composable
private fun LoadImageSlider(index: MutableState<Int>, modifier: Modifier) {
    val idImg = when (index.value) {
        0 -> R.drawable.slider_index_0
        1 -> R.drawable.slider_index_1
        else -> R.drawable.slider_index_2
    }
    Image(
        painter = painterResource(id = idImg),
        contentDescription = null,
        modifier = modifier
    )
}

@Composable
private fun BottomSheetOnBoardingPage(
    index: MutableState<Int>,
    navController: NavController? = null
) {
    val onClickSkip: () -> Unit = {
        navController?.navigate(ScreenPath.LOGIN_PAGE)
    }

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(topEnd = 32.dp))
            .background(Color.White)
            .padding(all = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text("Overall care", style = Typography.h1)
        Text(
            "Pet care combines with modern technology to bring the best experience of service.",
            modifier = Modifier.padding(top = 16.dp),
            style = Typography.body1
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            ButtonWithTextAndBackgroundOpposite(
                onClick = onClickSkip,
                title = "Login",
                isTextWhite = false,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.size(8.dp))
            ButtonWithTextAndBackgroundOpposite(
                onClick = onClickSkip,
                title = "Sign Up",
                modifier = Modifier.weight(1f)
            )
        }
        SlideDisplayInBottomSheet(
            onClickSkip = onClickSkip,
            onClickNext = {
                index.value++
            },
            index = index.value
        )
    }
}

@Composable
private fun SlideDisplayInBottomSheet(
    onClickSkip: () -> Unit,
    onClickNext: () -> Unit,
    index: Int
) {
    val canNext = index < 2
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Skip",
            style = Typography.body1,
            modifier = Modifier.clickable { onClickSkip() }
        )
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(3) {
                Spacer(
                    modifier = Modifier
                        .size(width = if (it == index) 32.dp else 16.dp, height = 8.dp)
                        .padding(start = 4.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(if (it == index) MaterialTheme.colors.primary else Color.Gray)
                )
            }
        }
        Text(
            text = if (canNext) "Next" else "Finish",
            modifier = Modifier.clickable {
                if (canNext) {
                    onClickNext()
                } else {
                    onClickSkip()
                }
            },
            style = MaterialTheme.typography.body1.copy(MaterialTheme.colors.primary)
        )
    }
}
