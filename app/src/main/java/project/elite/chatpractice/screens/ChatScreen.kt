package project.elite.chatpractice.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import project.elite.chatpractice.R

@Composable
fun ChatScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .padding(top = 35.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 35.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.background),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Pranave Ray",
                color = Color.White
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "",
                modifier = Modifier.size(25.dp),
                tint = Color.White
            )
        }


        Spacer(modifier = Modifier.height(16.dp))


        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(color = Color.White)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Chat messages
            ChatMessage(
                message = "Hey! How have you been?",
                isUserMe = false
            )
            ChatMessage(
                message = "Wanna catch up for a beer?",
                isUserMe = false
            )
            ChatMessage(
                message = "Awesome! Let’s meet up",
                isUserMe = true
            )
            ChatMessage(
                message = "Can I also get my cousin along?Will that be okay?",
                isUserMe = true
            )
            ChatMessage(
                message = "Yeah sure! get him too.",
                isUserMe = false
            )
            ChatMessage(
                message = "Alright! See you soon!",
                isUserMe = true
            )

        }
    }
}

@Composable
fun ChatMessage(message: String, isUserMe: Boolean) {
    val backgroundColor =
        if (isUserMe) Color(0xFFFF8933).copy(alpha = 0.25f) else Color(0xFFFFC700).copy(alpha = 0.25f)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        horizontalArrangement = if (isUserMe) Arrangement.End else Arrangement.Start
    ) {
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(backgroundColor),
            modifier = Modifier.widthIn(max = 250.dp)
        ) {
            Text(
                text = message,
                modifier = Modifier.padding(8.dp),
                color = Color.Black
            )

        }
    }
}