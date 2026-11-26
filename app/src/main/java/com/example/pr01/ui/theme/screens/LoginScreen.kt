package com.example.pr01.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pr01.ViewModel.LoginViewModel
import com.example.pr01.components.LoginHeaderLogo
import com.example.pr01.components.LoginTextField
import com.example.pr01.components.LoginTitle
import com.example.pr01.components.PrimaryButton
import com.example.pr01.data.AuthRequest

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel,
    onLoginSuccess: () -> Unit
) {
    val isAuthorized by viewModel.isAuthorized.collectAsStateWithLifecycle()

    LaunchedEffect(isAuthorized) {
        if (isAuthorized) {
            onLoginSuccess()
        }
    }

    LoginContent(
        modifier = modifier,
        onLoginClick = { email, password ->
            viewModel.login(AuthRequest(identity = email, password = password))
        }
    )
}

@Composable
fun LoginContent(
    modifier: Modifier = Modifier,
    onLoginClick: (String, String) -> Unit
) {
    var email by remember { mutableStateOf("bruno203@gmail.com") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 42.dp)
    ) {
        LoginHeaderLogo()

        Spacer(modifier = Modifier.height(30.dp))

        LoginTitle(
            modifier = Modifier.padding(start = 30.dp)
        )

        Spacer(modifier = Modifier.height(34.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            LoginTextField(
                label = "Email",
                value = email,
                onValueChange = { email = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            LoginTextField(
                label = "Password",
                value = password,
                onValueChange = { password = it },
                isPassword = true
            )

            Spacer(modifier = Modifier.height(30.dp))

            PrimaryButton(
                text = "Log in",
                onClick = { onLoginClick(email, password) }
            )
        }
    }
}

@Preview(device = "spec:width=375dp,height=812dp")
@Composable
private fun LoginScreenPreview() {
    LoginContent(onLoginClick = { _, _ -> })
}
