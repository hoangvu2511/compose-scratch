package com.vunguyenhoang.hapipet.feature.login

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.vunguyenhoang.hapipet.ui.theme.HapiPetTheme

@Composable
fun LogInPage(navController: NavController? = null) {
    Scaffold {

    }
}


@Preview
@Composable
private fun loadLoginPage() {
    HapiPetTheme {
        LogInPage()
    }
}