package project.elite.chatpractice.signin.phonesignin


import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import project.elite.chatpractice.signin.phonesignin.utils.getActivity
import java.util.concurrent.TimeUnit


val auth = FirebaseAuth.getInstance()
var storedVerificationId: String = ""


fun verifyPhoneNumberWithCode(context: Context,verificationId: String, code: String) {
    val credential = PhoneAuthProvider.getCredential(verificationId, code)
    signInWithPhoneAuthCredential(context,credential)
}

fun onLoginClicked (context: Context, phoneNumber: String,onCodeSent: () -> Unit) {

    auth.setLanguageCode("en")
    val callback = object: PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            Log.d("chatapp","verification completed")
            signInWithPhoneAuthCredential(context,credential)
        }

        override fun onVerificationFailed(p0: FirebaseException) {
            Log.d("chatapp", "verification failed$p0")
        }

        override fun onCodeSent(verificationId: String,
                                token: PhoneAuthProvider.ForceResendingToken) {
            Log.d("chatapp", "code sent$verificationId")
            storedVerificationId = verificationId
            onCodeSent()
        }

    }
    val options = context.getActivity()?.let {
        PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber("+91$phoneNumber")
            .setTimeout(60L,TimeUnit.SECONDS)
            .setActivity(it)
            .setCallbacks(callback)
            .build()
    }
    if (options != null) {
        Log.d("chatapp",options.toString())
        PhoneAuthProvider.verifyPhoneNumber(options)
    }
}

private fun signInWithPhoneAuthCredential(context: Context, credential: PhoneAuthCredential) {
    context.getActivity()?.let {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(it) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = task.result?.user
                    Log.d("chatapp","logged in")
                } else {
                    // Sign in failed, display a message and update the UI
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        Log.d("chatapp","wrong otp")
                    }
                    // Update UI
                }
            }
    }
}
