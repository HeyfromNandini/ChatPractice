package project.elite.chatpractice.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jet.firestore.JetFirestore
import com.jet.firestore.getListOfObjects
import project.elite.chatpractice.R
import project.elite.chatpractice.data.Person
import project.elite.chatpractice.data.UserInfo
import project.elite.chatpractice.navigation.Collections
import project.elite.chatpractice.navigation.Screens
import project.elite.chatpractice.signin.UserData
import project.elite.chatpractice.ui.theme.DarkGray
import project.elite.chatpractice.ui.theme.Gray
import project.elite.chatpractice.ui.theme.Gray400
import project.elite.chatpractice.ui.theme.Line
import project.elite.chatpractice.ui.theme.Yellow
import kotlin.random.Random

@Composable
fun HomeScreen(
    userData: UserData?,
    navController: NavController
) {

    var allUsers by remember { mutableStateOf<List<UserInfo>?>(null) }

    JetFirestore(path = {
//        collection(Collections.UserInfo.name)
                        collection("UserInfo")
    }, onRealtimeCollectionFetch = { values, _ ->
        allUsers = values?.getListOfObjects()
    }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            println("all users.$allUsers")


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 30.dp)
            ) {
                HeaderOrViewStory(userData, personList = emptyList())
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Color.White, RoundedCornerShape(
                                topStart = 30.dp, topEnd = 30.dp
                            )
                        )
                ) {
                    BottomSheetSwipeUp(
                        modifier = Modifier
                            .align(TopCenter)
                            .padding(top = 15.dp)
                    )
                    LazyColumn(
                        modifier = Modifier.padding(bottom = 15.dp, top = 30.dp)
                    ) {
                        items(allUsers ?: emptyList()) { userInfo ->
                            val user = Person(id= (0..100).random(),name = userInfo.name, icon = userInfo.image)
                            UserEachRow(user) {
                                navController.currentBackStackEntry?.savedStateHandle?.set(
                                    "data", user

                                )
                                navController.navigate(Screens.ChatScreen.route)
                            }
                        }
                    }
                }
            }

        }
    }

}

@Composable
fun HeaderOrViewStory(
    userData: UserData?,
    personList: List<Person>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 20.dp)
    ) {
        Header(userData = userData)
        LazyRow(modifier = Modifier.padding(vertical = 20.dp)) {
            item {
                AddStoryLayout()
                SpacerWidth()
            }
            items(personList) {
                UserStory(person = it)
            }
        }
    }
}

@Composable
fun BottomSheetSwipeUp(
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .background(
                Gray400,
                RoundedCornerShape(90.dp)
            )
            .width(90.dp)
            .height(5.dp)
    )
}

@Composable
fun UserEachRow(
    person: Person,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .noRippleEffect { onClick() }
            .padding(horizontal = 20.dp, vertical = 5.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    // Replace IconComponentDrawable with appropriate composable
                     IconComponentDrawable(icon = person.icon, size = 60.dp)
                    SpacerWidth()
                    Column {
                        person.name?.let {
                            Text(
                                text = it, style = TextStyle(
                                    color = Color.Black, fontSize = 15.sp, fontWeight = FontWeight.Bold
                                )
                            )
                        }
                        SpacerHeight(5.dp)
//                        Text(
//                            text = "okay", style = TextStyle(
//                                color = Gray, fontSize = 14.sp
//                            )
//                        )
                    }

                }
                Text(
                    text = "12:23pm", style = TextStyle(
                        color = Gray, fontSize = 12.sp
                    )
                )
            }
            SpacerHeight(15.dp)
            Divider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp, color = Line)
        }
    }

}

@Composable
fun UserStory(
    person: Person, modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(end = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .border(1.dp, Yellow, CircleShape)
                .background(Yellow, shape = CircleShape)
                .size(70.dp),
            contentAlignment = Alignment.Center
        ) {
            // Replace IconComponentDrawable with appropriate composable
             IconComponentDrawable(icon = person.icon, size = 65.dp)
        }
        SpacerHeight(8.dp)
        person.name?.let {
            Text(
                text = it, style = TextStyle(
                    color = Color.White, fontSize = 13.sp,
                ), modifier = Modifier.align(CenterHorizontally)
            )
        }

    }
}

@Composable
fun AddStoryLayout(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .border(2.dp, DarkGray, shape = CircleShape)
                .background(Yellow, shape = CircleShape)
                .size(70.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .background(Color.Black, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                // Replace IconComponentImageVector with appropriate composable
                 IconComponentImageVector(icon = Icons.Default.Add, size = 12.dp, tint = Yellow)
            }
        }
        SpacerHeight(8.dp)
        Text(
            text = "add story", style = TextStyle(
                color = Color.White, fontSize = 13.sp,
            ), modifier = Modifier.align(CenterHorizontally)
        )
    }
}

@Composable
fun Header(userData: UserData?) {
    val annotatedString = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.W300
            )
        ) {
            append("welcome back")
        }
        userData?.username?.let { username ->
            withStyle(
                style = SpanStyle(
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            ) {
                append(" $username")
            }
        }
    }

    Text(
        text = annotatedString,
        textAlign = TextAlign.Center,
        fontSize = 26.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color.White
    )
}

@SuppressLint("UnnecessaryComposedModifier", "UnrememberedMutableInteractionSource")
fun Modifier.noRippleEffect(onClick: () -> Unit): Modifier = composed {
    clickable(
        interactionSource = MutableInteractionSource(),
        indication = null
    ) {
        onClick()
    }
}
