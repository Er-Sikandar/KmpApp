package `in`.kmp.demoapp.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalUriHandler
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import `in`.kmp.demoapp.home.psn.HomeScreen
import `in`.kmp.demoapp.home.psn.HomeViewModel
import `in`.kmp.demoapp.modules.LocalAppContainer
import `in`.kmp.demoapp.notifications.psn.NotyScreen
import `in`.kmp.demoapp.notifications.psn.NotyViewModel
import `in`.kmp.demoapp.reglogin.psn.LoginScreen
import `in`.kmp.demoapp.reglogin.psn.RegLogViewModel
import `in`.kmp.demoapp.reglogin.psn.SignUpScreen
import `in`.kmp.demoapp.splash.SplashScreen


@Composable
fun KmpNavGraph( navController: NavHostController) {
    val container = LocalAppContainer.current
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
            val vModel = viewModel { RegLogViewModel(container.authModule.authRepo, container.storage) }
            LoginScreen(navSignUp = { navController.navigate(Screen.SignUp.route) },
                navHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                        launchSingleTop = true
                    }
            },
                vm=vModel
            )
        }
        composable(Screen.SignUp.route) {
            val vModel = viewModel { RegLogViewModel(container.authModule.authRepo, container.storage) }
            SignUpScreen( onBack= {
                navController.popBackStack()
            }, vm= vModel)
        }
        composable(Screen.Home.route) {
            val vModel = viewModel { HomeViewModel(container.homeModule.homeRepo, container.storage) }
            HomeScreen(homeViewModel = vModel,
                navHomeToWeb = { url, type ->

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