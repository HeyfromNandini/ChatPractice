package project.elite.chatpractice.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import project.elite.chatpractice.R

@Composable
fun HomeScreen() {

    Column(modifier = Modifier.fillMaxSize()) {


        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Welcome back, Nandini",
            fontSize = 25.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(20.dp))
        LazyRow {
            items(5) {
                Spacer(modifier = Modifier.width(10.dp))

                Column(modifier = Modifier.width(60.dp)) {
                    Card(shape = CircleShape, modifier = Modifier.size(50.dp)) {

                        Image(
                            painter = painterResource(id = R.drawable.background),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    Text(text = "Nandini Singh")
                }
            }



        }



        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(color = Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {

            Spacer(modifier = Modifier.height(30.dp))
            
            Divider(modifier = Modifier.width(100.dp), color = Color.Black)


            LazyColumn(modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(color = Color.White)
                .padding(top = 30.dp)){ items(10){


                Spacer(modifier = Modifier.height(10.dp))
                Row (modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp), horizontalArrangement = Arrangement.SpaceBetween){

                  Row() {

                      Card(shape = CircleShape, modifier = Modifier.size(60.dp)) {

                          Image(
                              painter = painterResource(id = R.drawable.background),
                              contentDescription = "",
                              contentScale = ContentScale.Crop,
                              modifier = Modifier.fillMaxSize()
                          )
                      }


                      Spacer(modifier = Modifier.width(10.dp))
                      Column {
                          Text(text = "Nandini Singh", color = Color.Black, fontWeight = FontWeight.Bold)
                          Text(text = "gvbyjhcbsjyhnjcnc", color = Color.Gray)
                      }
                  }


                    Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally) {


                        Text(text = " 12:30", color = Color.Gray)
                        Icon(imageVector = Icons.Default.Done, contentDescription = "")
                    }
                }
            }
            
            
        }
 

    }
    }
}