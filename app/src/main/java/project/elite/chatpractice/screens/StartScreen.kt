package project.elite.chatpractice.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import project.elite.chatpractice.R
import project.elite.chatpractice.navigation.Screens
import project.elite.chatpractice.signin.datastore.UserDatastore

@Composable
fun StartScreen(navHostController: NavController) {
    val context = LocalContext.current
    val datastore = UserDatastore(context)
    val name = datastore.getName.collectAsState(initial = "")
    val isLoggedIn = datastore.getLoginStatus.collectAsState(initial = false)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {


        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )



        Text(
            text = "Stay Connected with your family and friends",
            fontSize = 35.sp,
            lineHeight = 35.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 15.dp)
        )

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp), horizontalArrangement = Arrangement.Start) {


            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "",
                modifier = Modifier.size(25.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "Secure, private messaging", fontSize = 18.sp)
        }


        Button(onClick = {
//            navHostController.navigate(if (isLoggedIn.value) Screens.HomeScreen.route else Screens.SignInScreen.route)
            navHostController.navigate(Screens.SignInScreen.route)
        }, modifier = Modifier.padding(start = 40.dp, top = 20.dp)) {


            Text(text = "Get Started", fontSize = 16.sp, fontWeight = FontWeight.Bold)

        }
    }


}
