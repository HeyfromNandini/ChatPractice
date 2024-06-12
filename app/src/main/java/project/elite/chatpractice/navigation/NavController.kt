package project.elite.chatpractice.navigation

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch
import project.elite.chatpractice.screens.ChatScreen
import project.elite.chatpractice.screens.HomeScreen
import project.elite.chatpractice.screens.StartScreen
import project.elite.chatpractice.signin.GoogleAuthUiClient
import project.elite.chatpractice.signin.ProfileScreen
import project.elite.chatpractice.signin.SignInScreen
import project.elite.chatpractice.signin.SignInViewModel
import project.elite.chatpractice.signin.UserData
import project.elite.chatpractice.signin.datastore.UserDatastore

@Composable
fun MainNavController(
    navController: NavHostController,
) {


    val context = LocalContext.current
    val datastore = UserDatastore(context)
    val name = datastore.getName.collectAsState(initial = "")
    val isLoggedIn = datastore.getLoginStatus.collectAsState(initial = false)
    var userData by remember {
        mutableStateOf<UserData?>(null)
    }

    LaunchedEffect(key1 = isLoggedIn.value) {
        userData = UserData(userId = "ABC123", username = name.value, profilePictureUrl = null)
    }

    val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = context.applicationContext,
            oneTapClient = Identity.getSignInClient(context.applicationContext)
        )
    }
    val coroutineScope = rememberCoroutineScope()



    NavHost(
        navController = navController,
        startDestination = Screens.SplashScreen.route,
    ) {





        composable(Screens.SignInScreen.route) {
            val viewModel = viewModel<SignInViewModel>()
            val state by viewModel.state.collectAsState()

            LaunchedEffect(key1 = Unit) {
                if (googleAuthUiClient.getSignedInUser() != null) {
                    navController.navigate(Screens.HomeScreen.route)
                }
            }

            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartIntentSenderForResult(),
                onResult = { result ->
                    if (result.resultCode == Activity.RESULT_OK) {
                        coroutineScope.launch {
                            val signInResult = googleAuthUiClient.signInWithIntent(
                                intent = result.data ?: return@launch
                            )
                            viewModel.onSignInResult(signInResult)
                        }
                    }
                }
            )

            LaunchedEffect(key1 = state.isSignInSuccessful) {
                if (state.isSignInSuccessful) {
                    Toast.makeText(
                        context.applicationContext,
                        "Sign in successful",
                        Toast.LENGTH_LONG
                    ).show()

                    navController.navigate("profile")
                    viewModel.resetState()
                }
            }

            SignInScreen(
                navController= navController,
                state = state,
                onSignInClick = {
                    coroutineScope.launch {
                        val signInIntentSender = googleAuthUiClient.signIn()
                        launcher.launch(
                            IntentSenderRequest.Builder(
                                signInIntentSender ?: return@launch
                            ).build()
                        )
                    }
                }
            )
        }


        composable("profile") {
            ProfileScreen(
                navController = navController,
                userData = googleAuthUiClient.getSignedInUser(),
                onSignOut = {
                    coroutineScope.launch {
                        googleAuthUiClient.signOut()
                        Toast.makeText(
                            context.applicationContext,
                            "Signed out",
                            Toast.LENGTH_LONG
                        ).show()

                        navController.popBackStack()
                    }
                }
            )
        }


        composable(Screens.StartScreen.route) {
            StartScreen(navHostController = navController)
        }



        composable(Screens.HomeScreen.route) {
            HomeScreen(navHostController = navController)
        }


        composable(Screens.ChatScreen.route) {
            ChatScreen(navController = navController)
        }

        composable(Screens.SplashScreen.route) {
            SplashScreen(navController = navController)
        }


    }
}