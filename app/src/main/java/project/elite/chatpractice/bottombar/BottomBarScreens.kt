package project.elite.chatpractice.bottombar

import project.elite.chatpractice.R
import project.elite.chatpractice.navigation.Screens


sealed class BottomBarScreens(val route: String?, val title: String?, val icon: Int) {


    object Home : BottomBarScreens(
        Screens.HomeScreen.route,
        "Home",
        R.drawable.background
    )

    object Profile : BottomBarScreens(
        Screens.ProfileScreen.route,
        "Profile",
        R.drawable.background
    )

}

val items = listOf(
    BottomBarScreens.Home,
    BottomBarScreens.Profile,

    )