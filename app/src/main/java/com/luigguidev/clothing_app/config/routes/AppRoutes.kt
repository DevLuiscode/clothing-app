package com.luigguidev.clothing_app.config.routes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.vector.ImageVector
@Immutable
sealed class AppRoutes(
    val route: String,
) {
    @Immutable
    data object HomeScreenClothe : AppRoutes(route = "HomeScreenClothe")
    @Immutable
    data object AddScreenClothe : AppRoutes(route = "AddScreenClothe")
    @Immutable
    data object CardScreenClothe : AppRoutes(route = "CardScreenClothe")
    @Immutable
    data object CameraX : AppRoutes(route = "CameraX")


}
