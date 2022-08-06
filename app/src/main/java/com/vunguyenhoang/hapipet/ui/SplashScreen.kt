package com.vunguyenhoang.hapipet.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.vunguyenhoang.hapipet.R
import kotlinx.coroutines.delay

@Composable
fun AppSplashScreen(navigator: NavController? = null) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        LaunchedEffect(key1 = true) {
            delay(2000L)
            navigator?.navigate(ScreenPath.ON_BOARDING_PAGE){
                popUpTo(ScreenPath.ON_BOARDING_PAGE)
            }
        }
        val (logo) = createRefs()
        Image(
            painter = painterResource(id = R.drawable.ic_logo_with_text),
            contentDescription = null,
            modifier = Modifier.constrainAs(logo) {
                centerTo(parent)
            }
        )
    }
}

@Preview
@Composable
private fun previewSplashScreen() {
    AppSplashScreen()
}