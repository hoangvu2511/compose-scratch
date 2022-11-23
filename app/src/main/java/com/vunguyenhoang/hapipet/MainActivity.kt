package com.vunguyenhoang.hapipet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vunguyenhoang.hapipet.feature.forgetpassword.ForgetPasswordScreen
import com.vunguyenhoang.hapipet.feature.login.LogInPage
import com.vunguyenhoang.hapipet.feature.onboardingpage.OnBoardingPage
import com.vunguyenhoang.hapipet.ui.AppSplashScreen
import com.vunguyenhoang.hapipet.ui.ScreenPath
import com.vunguyenhoang.hapipet.ui.theme.HapiPetTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HapiPetTheme {
                LaunchScreen()
            }
        }
    }

    @Composable
    fun LaunchScreen() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = ScreenPath.SPLASH_SCREEN) {
            composable(ScreenPath.SPLASH_SCREEN) {
                AppSplashScreen(navController)
            }
            composable(ScreenPath.ON_BOARDING_PAGE) {
                OnBoardingPage(navController)
            }
            composable(ScreenPath.LOGIN_PAGE) {
                LogInPage(navController)
            }
            composable(ScreenPath.FORGET_PASSWORD_SCREEN) {
                ForgetPasswordScreen(navController)
            }
        }
    }
}
