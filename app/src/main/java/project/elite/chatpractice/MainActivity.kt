package project.elite.chatpractice

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.identity.Identity
import project.elite.chatpractice.bottombar.BottomBar
import project.elite.chatpractice.navigation.MainNavController
import project.elite.chatpractice.navigation.Screens
import project.elite.chatpractice.signin.GoogleAuthUiClient
import project.elite.chatpractice.ui.theme.ChatPracticeTheme




class MainActivity : ComponentActivity() {





    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatPracticeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val bottomBarState = rememberSaveable { mutableStateOf(true) }
                    val navBackStackEntry by navController.currentBackStackEntryAsState()

                    navBackStackEntry?.destination?.route?.let { route ->
                        bottomBarState.value = when (route) {
                            Screens.HomeScreen.route,
                            Screens.ProfileScreen.route,
                            Screens.ChatScreen.route -> true
                            else -> false
                        }
                    }

                    Scaffold(
                        bottomBar = {
                            BottomBar(
                                navController = navController,
                                bottomBarState = bottomBarState
                            )
                        }
                    ) {
                        val scaffoldState = rememberScaffoldState()
                        MainNavController(
                            navController
                        )
                    }
                }
            }
        }
    }
}
