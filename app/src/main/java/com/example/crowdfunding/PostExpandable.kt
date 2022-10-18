package com.example.crowdfunding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.gson.Gson

class PostExpandableActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userString = intent.extras?.getString("user")!!
        val user = Gson().fromJson(userString, User::class.java)

        setContent {
            PostContent(user)
        }
    }

    @Composable
    fun PostContent(user: User) {

        Scaffold(
            topBar = {
                TopBar()
            },
            bottomBar = {},
            backgroundColor = Color.White,
        )
        {
                Column() {
                    Box(
                        modifier = Modifier.padding(10.dp),
                        contentAlignment = Alignment.TopCenter
                    )
                    {
                        val selectedTabIndex by remember { mutableStateOf(0) }
                        ScrollableTabRow(
                            selectedTabIndex = selectedTabIndex,
                            backgroundColor = Color.Transparent, contentColor = Color.White,
                            edgePadding = 0.dp, modifier = Modifier.height(250.dp)
                        ) {
                            for (i in 0..user.pic.size - 1) {
                                Image(
                                    modifier = Modifier.height(250.dp),
                                    painter = painterResource(id = user.pic[i]),
                                    contentDescription = "pic",
                                    contentScale = ContentScale.FillWidth
                                )
                            }
                        }
                    }
                    Column() {
                        Row(modifier = Modifier.padding(start = 10.dp, end = 10.dp)) {
                            Text(text = user.text, color = Color.Black, fontSize = 20.sp)
                        }

                        Spacer(modifier = Modifier.height(13.dp))
                        Row(modifier = Modifier.padding(start = 10.dp, end = 10.dp)) {
                            Text(
                                text = "Funds to raise: Rs ",
                                color = Color.Black,
                                fontSize = 20.sp
                            )
                            Text(
                                text = user.amount.toString(),
                                color = Color.Black,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Button(
                            onClick = { /*TODO*/ },
                            enabled = true,
                            border = BorderStroke(width = 1.dp, brush = SolidColor(Color.Black)),
                            shape = MaterialTheme.shapes.medium,
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue)
                        ) {
                            Text(text = "Donate", color = Color.White)
                        }
                    }
                }
        }
    }
}