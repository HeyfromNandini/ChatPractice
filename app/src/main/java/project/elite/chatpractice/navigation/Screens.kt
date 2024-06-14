package project.elite.chatpractice.navigation


sealed class Screens(val route: String){


    object SignInScreen:Screens("sign_in")
    object HomeScreen:Screens("home")
    object StartScreen: Screens("start")
    object ChatScreen: Screens("chat")
    object SplashScreen: Screens("splash")
    object ProfileScreen: Screens("profile")
}




sealed class Collections(val name: String) {
    object AllChats: Collections("AllChats")
    object UserInfo: Collections("UserInfo")
}