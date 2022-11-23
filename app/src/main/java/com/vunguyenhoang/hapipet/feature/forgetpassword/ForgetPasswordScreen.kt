package com.vunguyenhoang.hapipet.feature.forgetpassword

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.vunguyenhoang.hapipet.R
import com.vunguyenhoang.hapipet.ui.widget.ButtonWithTextAndBackgroundOpposite
import com.vunguyenhoang.hapipet.ui.widget.HapiPetAppBar
import com.vunguyenhoang.hapipet.ui.widget.HapiPetKeyBoard
import com.vunguyenhoang.hapipet.ui.widget.PetTextField
import com.vunguyenhoang.hapipet.utils.validateEmail

@Composable
fun ForgetPasswordScreen(navHostController: NavHostController) {

    val (curStep, updateStep) = rememberSaveable {
        mutableStateOf(1)
    }
    val (code, updateCode) = remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(width = 0.dp, height = 16.dp))
        HapiPetAppBar(title = "Forget password?", onBackBtnClick = {
            navHostController.popBackStack()
        })
        AnimatedVisibility(visible = curStep == 1) {
            Text(
                text = "Please enter your email below to receive your password reset instructions.",
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 32.dp)
            )
        }
        Surface(
            color = Color.White,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.padding(top = 32.dp)
        ) {
            when (curStep) {
                1 -> SendEmail {
                    updateStep(curStep + 1)
                }
                2 -> VerifyCode(code) {
                    updateStep(curStep + 1)
                }
                3 -> CheckEmail {
                    navHostController.popBackStack()
                }
            }
        }
        if (curStep == 2) {
            Spacer(modifier = Modifier.weight(1f))
            HapiPetKeyBoard {
                val newCode = if (it != "remove") {
                    code + it
                } else {
                    code.dropLast(1)
                }
                if (newCode.length > 6) return@HapiPetKeyBoard
                updateCode(newCode)
            }
        }
    }
}


@Composable
private fun SendEmail(onClickSendEmail: (String) -> Unit) {
    val (emailField, updateEmailField) = remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.padding(12.dp)
    ) {
        Text(text = "Email", color = Color.Black)
        PetTextField(value = emailField, onValueChange = updateEmailField, placeHolder = {
            Text(text = "Your email...")
        })
        ButtonWithTextAndBackgroundOpposite(
            title = "Continue",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            onClick = { onClickSendEmail(emailField) },
            isEnabled = emailField.validateEmail()
        )
    }
}

@Composable
private fun VerifyCode(code: String, onContinueAfterSubmitCode: (String) -> Unit) {
    Column(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            (0..5).forEach {
                val backgroundColor = colorResource(id = R.color.grayScale)
                val borderColor = if (code.length == it)
                    colorResource(id = R.color.pink)
                else
                    colorResource(id = R.color.grayScale)

                Text(
                    text = (code.getOrNull(it) ?: "").toString(),
                    modifier = Modifier
                        .weight(1f)
                        .padding(12.dp)
                        .sizeIn(minHeight = 40.dp)
                        .background(
                            color = backgroundColor,
                            shape = MaterialTheme.shapes.small
                        )
                        .border(
                            width = 1.dp,
                            color = borderColor,
                            shape = MaterialTheme.shapes.medium
                        )
                )
            }
        }
        ButtonWithTextAndBackgroundOpposite(
            onClick = {
                onContinueAfterSubmitCode(code)
            },
            title = "Continue",
            isEnabled = code.length == 6,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun CheckEmail(onClickOk: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Successful")
        Text(text = "Please check email for instruction!")
        ButtonWithTextAndBackgroundOpposite(onClick = onClickOk, title = "Go back")
    }
}





























