package project.elite.chatpractice.signin

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import project.elite.chatpractice.R
import project.elite.chatpractice.signin.phonesignin.onLoginClicked
import project.elite.chatpractice.signin.phonesignin.storedVerificationId
import project.elite.chatpractice.signin.phonesignin.verifyPhoneNumberWithCode

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    navController: NavController,
    state: SignInState,
    onSignInClick: () -> Unit
) {
    val context = LocalContext.current


    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }


    val auth = FirebaseAuth.getInstance()
    var storedVerificationId: String = ""



//    val context = LocalContext.current
    var phoneNumber by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var otp by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var isOtpVisible by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // LottieAnimation for profile image
        val compnotify by rememberLottieComposition(
            spec = LottieCompositionSpec.Asset("profile.json")
        )
        val progress by animateLottieCompositionAsState(compnotify)
        LottieAnimation(
            composition = compnotify,
            iterations = Int.MAX_VALUE,
            isPlaying = true,
            contentScale = ContentScale.Crop,
            speed = 1.45f,
            modifier = Modifier
                .size(220.dp)
                .padding(2.dp)
                .clickable {
                    // Action when profile image clicked
                })

        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Signin", fontWeight = FontWeight.Bold, fontSize = 35.sp)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "via", fontSize = 18.sp)

        Spacer(modifier = Modifier.height(20.dp))

        // Google sign-in button
        Card(
            modifier = Modifier
                .size(80.dp)
                .clickable { onSignInClick() },
            shape = CircleShape
        ) {
            Image(
                painter = painterResource(id = R.drawable.google),
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
                contentDescription = ""
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = "OR", fontSize = 18.sp)

        Spacer(modifier = Modifier.height(10.dp))

        // Phone number field
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = {
                if (it.text.length <= 10) phoneNumber = it
            },
            label = { Text(text = "Phone No.", color = Color.Gray) },
            placeholder = { Text(text = "Name", fontSize = 14.sp) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .size(60.dp)

        )

        Spacer(modifier = Modifier.height(30.dp))

        if(isOtpVisible) {
            androidx.compose.material.TextField(
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White
                ),
                value = otp,
                placeholder = { androidx.compose.material.Text("Enter otp") },
                onValueChange = { otp = it },
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(top = 4.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }

        if(!isOtpVisible) {
            Row( modifier = Modifier
                .padding(top = 8.dp)
                .padding(horizontal = 50.dp)) {
                androidx.compose.material.Button(
                onClick = {
                    onLoginClicked(context, phoneNumber.text) {
                        Log.d("phoneBook", "setting otp visible")
                        isOtpVisible = true
                    }
                },
                colors = ButtonDefaults.textButtonColors(
                    backgroundColor = androidx.compose.material.MaterialTheme.colors.primary
                ),
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(top = 8.dp)
            ) {
                androidx.compose.material.Text(text = "Send otp", color = Color.White)
            }}
        }


        if(isOtpVisible) {

            Row( modifier = Modifier
                .padding(top = 8.dp)
                .padding(horizontal = 50.dp)) {
                androidx.compose.material.Button(
                    onClick = { verifyPhoneNumberWithCode(context, storedVerificationId, otp.text) },
                    colors = ButtonDefaults.textButtonColors(
                        backgroundColor = androidx.compose.material.MaterialTheme.colors.primary
                    ),
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(top = 8.dp)

                ) {
                    androidx.compose.material.Text(text = "Verify", color = Color.White)
                }
            }
        }



        Spacer(modifier = Modifier.height(30.dp))


    }
}
