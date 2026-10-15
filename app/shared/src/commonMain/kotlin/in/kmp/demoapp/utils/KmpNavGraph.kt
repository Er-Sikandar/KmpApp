package `in`.kmp.demoapp.utils

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import `in`.kmp.demoapp.home.HomeScreen
import `in`.kmp.demoapp.notifications.psn.NotyScreen
import `in`.kmp.demoapp.notifications.psn.NotyViewModel
import `in`.kmp.demoapp.reglogin.LoginScreen
import `in`.kmp.demoapp.reglogin.SignUpScreen
import `in`.kmp.demoapp.splash.SplashScreen


@Composable
fun KmpNavGraph( navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(navLogin = {
                navController.navigate(Screen.Login.route) {
                    popUpTo(Screen.Splash.route) { inclusive = true }
                    launchSingleTop = true
                }
              },
              navHome = {
                  navController.navigate(Screen.Home.route) {
                      popUpTo(Screen.Splash.route) { inclusive = true }
                      launchSingleTop = true
                  }
            })
        }
        composable(Screen.Login.route) {
            LoginScreen(navSignUp = { navController.navigate(Screen.SignUp.route) },
                navHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                        launchSingleTop = true
                    }
            })
        }
        composable(Screen.SignUp.route) {
            SignUpScreen( onBack= {
                navController.popBackStack()
            })
        }
        composable(Screen.Home.route) {
            HomeScreen(navHomeToWeb = { url, type ->

                 },
                navHomeToLogin = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(navController.graph.id) { inclusive = true }
                        launchSingleTop = true
                    }
                },
                navHomeToNoty = {
                    navController.navigate(Screen.Noty.route)
                },
                onExitApp = {

            })
        }
        composable(Screen.Noty.route) {
            val nViewModel: NotyViewModel = viewModel { NotyViewModel() }
            NotyScreen(onBack = {
                navController.popBackStack()
            },
            viewModel= nViewModel
            )
        }


    }
}