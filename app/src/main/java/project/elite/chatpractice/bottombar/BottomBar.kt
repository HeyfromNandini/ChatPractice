package project.elite.chatpractice.bottombar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import project.elite.chatpractice.navigation.Screens

@Composable
fun BottomBar(
    navController: NavController,
    bottomBarState: MutableState<Boolean> = mutableStateOf(true)
) {
    if (bottomBarState.value) {
        BoxWithConstraints(
            modifier = Modifier.fillMaxSize()
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination

            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    items.forEachIndexed { index, item ->

                        BottomNavigationItem(
                            icon = {
                                item.icon?.let {
                                    Icon(
                                        painter = painterResource(id = it),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .size(35.dp)
                                            .padding(bottom = 5.dp),
                                        tint = Color.Blue
                                    )
                                }
                            },
                            label = {
                                item.title?.let {
                                    Text(
                                        text = it,
                                        color = Color.Black,
                                        softWrap = true,
                                        fontSize = 8.5.sp
                                    )
                                }
                            },
                            selected = currentRoute?.hierarchy?.any { nav ->
                                nav.route == item.route
                            } == true,
                            selectedContentColor = Color.Yellow,
                            unselectedContentColor = Color.White,
                            modifier = Modifier
                                .weight(1f)
                                .background(Color.White),
                            onClick = {
                                item.route?.let { route ->
                                    navController.navigate(route) {
                                        popUpTo(Screens.ProfileScreen.route) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}
