package com.sign.googlein.auth_feature.presentation.util

sealed class Screen(val route: String){
    data object Login : Screen("login")
    data object Profile: Screen("profile")
}
