package `in`.kmp.demoapp.utils

import `in`.kmp.demoapp.utils.Const.SPLASH_ROUTE
import `in`.kmp.demoapp.utils.Const.LOGIN_ROUTE
import `in`.kmp.demoapp.utils.Const.HOME_ROUTE
import `in`.kmp.demoapp.utils.Const.NOTY_ROUTE
import `in`.kmp.demoapp.utils.Const.SIGNUP_ROUTE

sealed class Screen(val route: String) {
    data object Splash : Screen(SPLASH_ROUTE)
    data object Login : Screen(LOGIN_ROUTE)
    data object SignUp : Screen(SIGNUP_ROUTE)
    data object Home : Screen(HOME_ROUTE)
    data object Noty : Screen(NOTY_ROUTE)
}