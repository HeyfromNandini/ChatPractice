package project.elite.chatpractice.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import project.elite.chatpractice.R
import project.elite.chatpractice.navigation.Screens


sealed class BottomBarScreens(val route: String?, val title: String?, val icon: ImageVector) {


    object Home : BottomBarScreens(
        Screens.HomeScreen.route,
        "Home",
        Icons.Default.AccountBalance
    )

    object Profile : BottomBarScreens(
        Screens.ProfileScreen.route,
        "Profile",
        Icons.Default.Person
    )

}

val items = listOf(
    BottomBarScreens.Home,
    BottomBarScreens.Profile,

    )