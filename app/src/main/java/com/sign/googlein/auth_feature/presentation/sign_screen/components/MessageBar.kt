package com.sign.googlein.auth_feature.presentation.sign_screen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sign.googlein.auth_feature.domain.model.MessageBarState
import com.sign.googlein.ui.theme.ErrorBg
import com.sign.googlein.ui.theme.SuccessBg
import io.ktor.client.network.sockets.SocketTimeoutException
import kotlinx.coroutines.delay
import java.net.ConnectException

@Composable
fun MessageBar(
    state: MessageBarState
) {
    var startAnimation by remember{ mutableStateOf(false) }
    var errorMessage by remember{ mutableStateOf("") }

    LaunchedEffect(key1 = state){
        state.exception?.let{error ->
            errorMessage = when(error){
                is SocketTimeoutException ->{
                    "Connection timeout exception"
                }
                is ConnectException ->{
                    "Internet Connection Unavailable"
                }
                else ->{
                    "${error.message}"
                }
            }
        }
        startAnimation = true
        delay(3000)
        startAnimation = false
    }
    
    AnimatedVisibility(
        visible = state.exception != null && startAnimation
                || state.message != null && startAnimation,
        enter = expandVertically(
            animationSpec = tween(300),
            expandFrom = Alignment.Top
        ),
        exit = shrinkVertically(
            animationSpec = tween(300),
            shrinkTowards = Alignment.Top
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(if (state.exception != null) ErrorBg else SuccessBg)
                .padding(horizontal = 20.dp)
                .height(40.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                imageVector = if(state.exception != null) Icons.Default.Warning
                    else Icons.Default.Check,
                contentDescription = "Message Icon",
                tint = Color.White
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = if(state.exception != null) errorMessage else state.message.toString(),
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
    }
}