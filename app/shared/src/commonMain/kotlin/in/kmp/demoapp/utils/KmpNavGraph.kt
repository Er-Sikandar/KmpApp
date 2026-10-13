package `in`.kmp.demoapp.utils

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import `in`.kmp.demoapp.home.HomeScreen
import `in`.kmp.demoapp.notifications.NotyScreen
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
                popUpTo(navController.graph.startDestinationId) {
                    inclusive = true
                }
            }},
              navHome = {
                  navController.navigate(Screen.Home.route) {
                      popUpTo(navController.graph.startDestinationId) {
                          inclusive = true
                      }
                  }
            })
        }
        composable(Screen.Login.route) {
            LoginScreen(navSignUp = { navController.navigate(Screen.SignUp.route) },
                navHome = {
                navController.navigate(Screen.Home.route) {
                    popUpTo(navController.graph.startDestinationId) {
                        inclusive = true
                    }
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

                },
                navHomeToNoty = {
                    navController.navigate(Screen.Noty.route)
                },
                onExitApp = {

            })
        }
        composable(Screen.Noty.route) {
            NotyScreen(onBack = {
                navController.popBackStack()
            })
        }


    }
}