package com.baisamy.studentsejournal.presentation.screen.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.baisamy.studentsejournal.R
import com.baisamy.studentsejournal.data.model.request.LoginRequest
import com.baisamy.studentsejournal.presentation.models.ActionType
import com.baisamy.studentsejournal.presentation.navigation.MainNavigation
import com.baisamy.studentsejournal.presentation.ui.components.ActionButton
import com.baisamy.studentsejournal.presentation.ui.components.MainTextField
import com.baisamy.studentsejournal.presentation.ui.theme.Body
import com.baisamy.studentsejournal.presentation.ui.theme.Headline
import com.baisamy.studentsejournal.presentation.ui.theme.LightBlue
import com.baisamy.studentsejournal.presentation.ui.theme.Red
import com.baisamy.studentsejournal.presentation.ui.theme.White
import com.baisamy.studentsejournal.presentation.ui.theme.WhiteGray
import com.baisamy.studentsejournal.presentation.viewmodel.SharedViewModel
import com.baisamy.studentsejournal.utils.isEmailValid
import com.baisamy.studentsejournal.utils.isPasswordValid

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navHostController: NavHostController,
    sharedViewModel: SharedViewModel
) {

    val token by sharedViewModel.token.observeAsState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var emailIsValid by remember { mutableStateOf(true) }
    var passwordIsValid by remember { mutableStateOf(true) }

    if (!token.isNullOrBlank()) {
        sharedViewModel.loadCourses(email)
        navHostController.navigate(MainNavigation.HomeScreen.route)
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = White,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(id = R.string.login).uppercase(),
                        style = Headline
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = LightBlue,
                    titleContentColor = White,
                    actionIconContentColor = White,
                    navigationIconContentColor = White
                )
            )
        }
    ) { paddingValues ->
        val topPadding = paddingValues.calculateTopPadding()
        Box(
            modifier = Modifier
                .padding(top = topPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Surface(
                shape = RoundedCornerShape(10.dp),
                shadowElevation = 4.dp
            ) {
                Box(
                    modifier = Modifier
                        .width(312.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(WhiteGray)
                        .padding(vertical = 16.dp, horizontal = 24.dp),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Column {
                        MainTextField(
                            value = email,
                            onValueChange = {
                                email = it
                                emailIsValid = isEmailValid(it)
                            },
                            hint = stringResource(id = R.string.email)
                        )
                        if (!emailIsValid) {
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = stringResource(id = R.string.wrong_email),
                                style = Body,
                                color = Red
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        MainTextField(
                            value = password,
                            onValueChange = {
                                password = it
                                passwordIsValid = isPasswordValid(it)
                            },
                            hint = stringResource(id = R.string.password)
                        )
                        if (!passwordIsValid) {
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = stringResource(id = R.string.wrong_password),
                                style = Body,
                                color = Red
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        ActionButton(
                            title = stringResource(id = R.string.register),
                            actionType = ActionType.SECONDARY
                        ) {
                            navHostController.navigate(MainNavigation.RegisterScreen.route)
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        ActionButton(
                            title = stringResource(id = R.string.login),
                            actionType = ActionType.PRIMARY
                        ) {
                            if (emailIsValid && email.isNotBlank() && passwordIsValid && password.isNotBlank()) {
                                sharedViewModel.authenticate(
                                    LoginRequest(
                                        email = email,
                                        password = password
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = false)
fun LoginScreenPreview() {
    LoginScreen(rememberNavController(), SharedViewModel())
}