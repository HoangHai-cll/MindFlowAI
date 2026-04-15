package vn.humg.hai.mindflowai.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import vn.humg.hai.mindflowai.ui.navigation.Screen
import vn.humg.hai.mindflowai.ui.theme.AccentCyan
import vn.humg.hai.mindflowai.ui.theme.DarkBg

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val screens = listOf(Screen.Explore, Screen.Map, Screen.Profile)

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = DarkBg,
                contentColor = AccentCyan
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                screens.forEach { screen ->
                    val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = null) },
                        label = { Text(screen.title) },
                        selected = selected,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = AccentCyan,
                            unselectedIconColor = Color.Gray,
                            indicatorColor = Color.Transparent
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Explore.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Explore.route) { 
                ExploreScreen(
                    onNavigateToMap = { topicId ->
                        navController.navigate(Screen.Map.route)
                    }
                ) 
            }
            composable(Screen.Map.route) { MapScreen() }
            composable(Screen.Profile.route) { ProfileScreen() }
        }
    }
}
