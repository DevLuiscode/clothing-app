package com.luigguidev.clothing_app.config.routes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class AppRoutes(
    val route: String,
    val icon: ImageVector,
) {
    data object HomeScreenClothe : AppRoutes(route = "HomeScreenClothe", icon = Icons.Outlined.Home)
    data object AddScreenClothe : AppRoutes(route = "AddScreenClothe", icon = Icons.Outlined.Add)
    data object CardScreenClothe :
        AppRoutes(route = "CardScreenClothe", icon = Icons.Outlined.ShoppingCart)

}
