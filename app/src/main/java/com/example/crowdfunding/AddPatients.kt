package com.example.crowdfunding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class AddPatientsActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AddPatients()
        }
    }


    @Composable
    fun AddPatients() {
        Scaffold(
            topBar = {
                TopBar1()
            },
            bottomBar = {
                        BottomAppBar(backgroundColor = Color.Blue) {
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center,) {
                                Text(
                                    text = "Submit",
                                    color = Color.White,
                                    fontSize = 20.sp,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
            },
            backgroundColor = Color.Black,
        )
        {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(10.dp),
                shape = RoundedCornerShape(16.dp),
                backgroundColor = Color.White
            )
            {
                val scrollState = rememberScrollState()
                Column(modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState), horizontalAlignment = Alignment.CenterHorizontally) {
                    Row(modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(), horizontalArrangement = Arrangement.Center)
                    {
                        Text(text = "Tell us about the patient",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                        )
                    }
                    Divider()
                    Spacer(modifier = Modifier.height(10.dp))
                    var text1 by remember { mutableStateOf("") }
                    OutlinedTextField(
                        value = text1,
                        onValueChange = { newText ->
                        text1=newText },
                        keyboardOptions = KeyboardOptions
                            (
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        label = { Text(text="Patient's full name")},
                        maxLines = 1,
                        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White, focusedIndicatorColor = Color.Blue, unfocusedIndicatorColor = Color.Black, focusedLabelColor = Color.Blue),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    var text2 by remember { mutableStateOf("") }
                    OutlinedTextField(
                        value = text2,
                        onValueChange = { newText ->
                            text2=newText },
                        keyboardOptions = KeyboardOptions
                            (
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        label = { Text(text="Patient's age")},
                        maxLines = 1,
                        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White, focusedIndicatorColor = Color.Blue, unfocusedIndicatorColor = Color.Black, focusedLabelColor = Color.Blue),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    var text3 by remember { mutableStateOf("") }
                    OutlinedTextField(
                        value = text3,
                        onValueChange = { newText ->
                            text3=newText },
                        keyboardOptions = KeyboardOptions
                            (
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        label = { Text(text="Enter details about the patient")},
                        maxLines = 8,
                        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White, focusedIndicatorColor = Color.Blue, unfocusedIndicatorColor = Color.Black, focusedLabelColor = Color.Blue),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    var text4 by remember { mutableStateOf("") }
                    OutlinedTextField(
                        value = text4,
                        onValueChange = { newText ->
                            text4=newText },
                        keyboardOptions = KeyboardOptions
                            (
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        label = { Text(text="How much do you want to raise?")},
                        maxLines = 1,
                        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White, focusedIndicatorColor = Color.Blue, unfocusedIndicatorColor = Color.Black, focusedLabelColor = Color.Blue),
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = {
                            Icon(painter = painterResource(id = R.drawable.rupee) , contentDescription = "rupee")
                        }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Box(modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .border(
                            brush = SolidColor(Color.Black),
                            width = 1.dp,
                            shape = RectangleShape
                        )){
                        Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
                            Text(
                                text = "Add image of patient",
                                color = Color.Black,
                                fontSize = 18.sp,
                                modifier = Modifier.padding(7.dp)
                            )
                            Spacer(modifier = Modifier.width(2.dp))
                            Button(
                                onClick = { /*TODO*/ },
                                enabled = true,
                                border = BorderStroke(width = 1.dp, brush = SolidColor(Color.Blue)),
                                shape = MaterialTheme.shapes.medium,
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent, contentColor = Color.Blue)
                            ) {
                                Text(text = "Upload")
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Image(
                        modifier = Modifier.height(250.dp),
                        painter = painterResource(id = R.drawable.pic1),
                        contentDescription = "upload",
                        contentScale = ContentScale.FillWidth
                    )
                }
            }//card
        }//scaffold
    }

}