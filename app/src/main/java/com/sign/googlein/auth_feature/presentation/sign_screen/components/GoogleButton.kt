package com.sign.googlein.auth_feature.presentation.sign_screen.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
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
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sign.googlein.R

@Composable
fun GoogleButton(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    primText: String = "Sign in with Google",
    secText: String = "Please wait...",
    icon: Int = R.mipmap.google_icon,
    borderColor: Color = Color.LightGray,
    back: Color = MaterialTheme.colorScheme.background,
    progressColor: Color = Color.Blue
) {
    var buttonText by remember{ mutableStateOf(primText) }

    LaunchedEffect(key1 = isLoading){
        buttonText = if(isLoading) secText else primText
    }

    Surface(
        modifier = modifier,
        border = BorderStroke(1.dp, borderColor),
        color = back
    ){
        Row(
            modifier = Modifier
                .padding(12.dp)
                .animateContentSize(
                    animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "Google icon",
                tint = Color.Unspecified
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(text = buttonText)
            if(isLoading){
                Spacer(modifier = Modifier.width(16.dp))
                CircularProgressIndicator(
                    modifier = Modifier.size(16.dp),
                    strokeWidth = 2.dp,
                    color = progressColor
                )
            }

        }

    }

}


@Composable
@Preview
fun GoogleButtonPreview() {
    GoogleButton()
}