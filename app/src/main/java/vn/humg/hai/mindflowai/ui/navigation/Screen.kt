package vn.humg.hai.mindflowai.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Map
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Explore : Screen("explore", "Khám phá", Icons.Default.Explore)
    object Map : Screen("map", "Bản đồ", Icons.Default.Map)
    object Profile : Screen("profile", "Hồ sơ", Icons.Default.AccountCircle)
}
