package com.sign.googlein.auth_feature.presentation.sign_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.sign.googlein.R
import com.sign.googlein.auth_feature.domain.model.MessageBarState
import com.sign.googlein.auth_feature.presentation.sign_screen.components.GoogleButton
import com.sign.googlein.ui.theme.ErrorBg
import com.sign.googlein.ui.theme.SuccessBg
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignScreen(controller: NavHostController) {

    val snackBarState = remember {
        SnackbarHostState()
    }

    val scope = rememberCoroutineScope()

    var messageState by remember {
        mutableStateOf(MessageBarState())
    }

    LaunchedEffect(key1 = messageState){

    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarState){data ->
                Snackbar(
                    snackbarData = data,
                    containerColor = if(messageState.message != null) ErrorBg else SuccessBg
                )
            }
        }
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(
                modifier = Modifier.size(50.dp),
                painter = painterResource(id = R.mipmap.google_icon),
                contentDescription = "Google Image"
            )
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Sign in to Continue",
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 26.sp
            )

            Text(
                modifier = Modifier.width(70.dp),
                text = "If you want to access this app, first you need to sign in",
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Light,
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(20.dp))
            GoogleButton(modifier = Modifier.clickable {
                scope.launch {
                    snackBarState.showSnackbar(
                        message = messageState.message ?: "Success",

                    )
                }
            })

        }
    }
}