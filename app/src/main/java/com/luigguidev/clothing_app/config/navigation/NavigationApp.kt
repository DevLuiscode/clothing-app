package com.luigguidev.clothing_app.config.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.luigguidev.clothing_app.config.routes.AppRoutes
import com.luigguidev.clothing_app.ui.addScreenClothe.AddScreenClothe
import com.luigguidev.clothing_app.ui.cardScreen.CardScreen
import com.luigguidev.clothing_app.ui.components.CameraXComponent
import com.luigguidev.clothing_app.ui.homeScreenClothe.HomeScreenClothe
import com.luigguidev.clothing_app.viewmodel.CardScreenViewModel
import com.luigguidev.clothing_app.viewmodel.ClotheScreenViewModel

@Composable
fun NavigationApp(
    clotheScreenViewModel: ClotheScreenViewModel,
    cardScreenViewModel: CardScreenViewModel,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = AppRoutes.HomeScreenClothe.route,
    ) {
        composable(
            AppRoutes.HomeScreenClothe.route,
            enterTransition = {
                slideInVertically(
                    animationSpec = tween(300),
                    initialOffsetY = { it } // from bottom
                )
            },
            exitTransition = {
                slideOutVertically(
                    animationSpec = tween(300),
                    targetOffsetY = { it }
                )
            }
        ) {
            HomeScreenClothe(
                viewModelNavControllerState = ViewModelNavControllerState(
                    clotheScreenViewModel = clotheScreenViewModel,
                ),
                cardViewModelState = CardViewModelState(
                    cardScreenViewModel = cardScreenViewModel
                ),
                onClick = {
                    navController.navigate(AppRoutes.AddScreenClothe.route) {
                        popUpTo(AppRoutes.HomeScreenClothe.route)
                    }
                },
                onClickCardNavigation = {
                    navController.navigate(AppRoutes.CardScreenClothe.route) {
                        popUpTo(AppRoutes.HomeScreenClothe.route)
                    }
                }
            )
        }
        composable(
            AppRoutes.AddScreenClothe.route,
            enterTransition = {
                slideInVertically(
                    animationSpec = tween(300),
                    initialOffsetY = { it } // from bottom
                )
            },
            exitTransition = {
                slideOutVertically(
                    animationSpec = tween(300),
                    targetOffsetY = { it }
                )
            }
        ) {
            AddScreenClothe(
                viewModelNavControllerState = ViewModelNavControllerState(
                    clotheScreenViewModel = clotheScreenViewModel,
                ),
                onClick = {
                    navController.navigate(AppRoutes.HomeScreenClothe.route) {
                        popUpTo(AppRoutes.AddScreenClothe.route) {
                            inclusive = false
                        }
                    }
                },
                onClickCamera = {
                    navController.navigate(AppRoutes.CameraX.route) {
                        popUpTo(AppRoutes.AddScreenClothe.route) {
                            inclusive = false
                        }
                    }
                }
            )
        }
        composable(
            AppRoutes.CameraX.route,
            enterTransition = {
                slideInVertically(
                    animationSpec = tween(300),
                    initialOffsetY = { it } // from bottom
                )
            },
            exitTransition = {
                slideOutVertically(
                    animationSpec = tween(300),
                    targetOffsetY = { it }
                )
            }
        ) {
            CameraXComponent(
                viewModelNavControllerState = ViewModelNavControllerState(
                    clotheScreenViewModel = clotheScreenViewModel,
                )
            )
        }
        composable(
            AppRoutes.CardScreenClothe.route,
            enterTransition = {
                slideInVertically(
                    animationSpec = tween(300),
                    initialOffsetY = { it } // from bottom
                )
            },
            exitTransition = {
                slideOutVertically(
                    animationSpec = tween(300),
                    targetOffsetY = { it }
                )
            }
        ) {
            CardScreen(
                cardViewModelState = CardViewModelState(
                    cardScreenViewModel = cardScreenViewModel
                ),
                cardScreenViewModel = cardScreenViewModel
            )
        }
    }
}

@Immutable
data class ViewModelNavControllerState(
    val clotheScreenViewModel: ClotheScreenViewModel,
)

@Immutable
data class CardViewModelState(
    val cardScreenViewModel: CardScreenViewModel
)

