@file:OptIn(ExperimentalPagerApi::class)

package com.vunguyenhoang.hapipet.feature.login

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.vunguyenhoang.hapipet.R
import com.vunguyenhoang.hapipet.ui.ScreenPath
import com.vunguyenhoang.hapipet.ui.theme.HapiPetTheme
import com.vunguyenhoang.hapipet.ui.widget.ButtonWithTextAndBackgroundOpposite
import com.vunguyenhoang.hapipet.ui.widget.PetTextField
import com.vunguyenhoang.hapipet.ui.widget.SocialButton
import kotlinx.coroutines.launch

@Composable
fun LogInPage(navController: NavController? = null) {
    val pageState = rememberPagerState(
        pageCount = 2,
        initialPage = 0,
        initialOffscreenLimit = 2
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_logo_with_text),
            contentDescription = null
        )
        Image(
            painter = painterResource(id = R.drawable.ic_illus_in_login_page),
            contentDescription = null
        )
        Surface(
            modifier = Modifier
                .weight(1f),
            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
            elevation = 4.dp,
            color = Color.White
        ) {
            Column(
                modifier = Modifier
                    .weight(1f, false)
            ) {
                val modifier = Modifier.weight(1f, false)
                LoadTab(pageState = pageState)
                HorizontalPager(state = pageState) { index ->
                    when (index) {
                        0 -> LoadSignIn(navController = navController, modifier)
                        else -> LoadSignUp(navController = navController, modifier)
                    }
                }
            }
        }

    }
}

@Composable
private fun LoadTab(pageState: PagerState) {
    val arrTabs = arrayListOf("Sign In", "Sign Up")
    val coroutineScope = rememberCoroutineScope()
    TabRow(selectedTabIndex = pageState.currentPage) {
        arrTabs.forEachIndexed { idx, str ->
            val tabSelected = pageState.currentPage == idx
            val textColor = if (tabSelected) colorResource(id = R.color.pink) else Color.Gray
            Tab(selected = tabSelected, onClick = {
                coroutineScope.launch {
                    if (!tabSelected) {
                        pageState.animateScrollToPage(idx)
                    }
                }
            }, modifier = Modifier.background(Color.White)) {
                Text(
                    text = str,
                    style = MaterialTheme.typography.h3.copy(color = textColor),
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    }
}

@Composable
private fun LoadSignIn(navController: NavController?, modifier: Modifier) {
    val (emailField, setEmail) = remember {
        mutableStateOf("")
    }
    val (passwordField, setPassword) = remember {
        mutableStateOf("")
    }
    Column(
        modifier = modifier
            .padding(all = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(text = "Email", style = MaterialTheme.typography.h6)
        PetTextField(value = emailField, onValueChange = setEmail)
        Text(
            text = "Password",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(top = 16.dp)
        )
        PetTextField(
            value = passwordField,
            onValueChange = setPassword,
            keyboardType = KeyboardType.Password
        )
        Text(
            "Forget password?",
            style = MaterialTheme.typography.h6.copy(
                color = Color.Blue,
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier
                .padding(top = 16.dp)
                .clickable {
                    navController?.navigate(ScreenPath.FORGET_PASSWORD_SCREEN)
                }
        )
        ButtonWithTextAndBackgroundOpposite(
            onClick = {},
            title = "Continue",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        )
        Column(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Log in with social account", style = MaterialTheme.typography.body1)
            Row(modifier = Modifier.padding(top = 16.dp)) {
                SocialButton(
                    icon = ImageVector.vectorResource(id = R.drawable.ic_facebook),
                    backgroundColor = Color(0xFF3B5998),
                    btnTitle = "FaceBook",
                    modifier = Modifier.weight(1f),
                    onClick = {

                    }
                )
                Spacer(modifier = Modifier.size(16.dp))
                SocialButton(
                    icon = ImageVector.vectorResource(id = R.drawable.ic_twitter),
                    modifier = Modifier.weight(1f),
                    backgroundColor = Color(0xFF44BAF1),
                    btnTitle = "Twitter",
                    onClick = {

                    }
                )
            }
        }
    }
}

@Composable
private fun LoadSignUp(navController: NavController?, modifier: Modifier) {
    val (nameField, updateNameField) = remember {
        mutableStateOf("")
    }
    val (emailField, updateEmailField) = remember {
        mutableStateOf("")
    }
    val (passwordField, updatePasswordField) = remember {
        mutableStateOf("")
    }
    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        Text(
            text = "Your name",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(top = 16.dp)
        )
        PetTextField(value = nameField, onValueChange = updateNameField)

        Text(
            text = "Email",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(top = 16.dp)
        )
        PetTextField(value = emailField, onValueChange = updateEmailField)

        Text(
            text = "Your name",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(top = 16.dp)
        )
        PetTextField(
            value = passwordField,
            onValueChange = updatePasswordField,
            keyboardType = KeyboardType.Password
        )
        ButtonWithTextAndBackgroundOpposite(
            onClick = { },
            title = "Create Account",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        )
    }
}


@Preview(device = Devices.NEXUS_5)
@Composable
private fun loadLoginPage() {
    HapiPetTheme {
        LogInPage()
    }
}