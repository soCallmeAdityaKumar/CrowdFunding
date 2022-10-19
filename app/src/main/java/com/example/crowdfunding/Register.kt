package com.example.crowdfunding

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.crowdfunding.LoginActivity
import com.example.crowdfunding.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.ktx.auth


class RegisterActivity : ComponentActivity() {
    companion object{
        val TAG : String=LoginActivity::class.java.simpleName
    }
    private val auth by lazy{
        Firebase.auth
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                ShowForm(auth)
        }
    }
}

@Composable
fun ShowForm(auth:FirebaseAuth){

    val focusManager= LocalFocusManager.current
    val context= LocalContext.current

    var email by remember{
        mutableStateOf("")
    }

    var Password by remember{
        mutableStateOf("")
    }
    var name by remember{
        mutableStateOf("")
    }
    var phone by remember{
        mutableStateOf("")
    }
    val isEmailvalid  by derivedStateOf {
        Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    var confirmPassword by remember{
        mutableStateOf("")
    }
    val isPasswordvalid by derivedStateOf {
        Password.length>7
    }
    var isPasswordremember by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .background(color = Color.LightGray)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text="Register for fund Raising",
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            fontSize = 32.sp,
            modifier = Modifier.padding(top =16.dp)

        )
//        Icon(painter = painterResource(id = R.drawable.ic_baseline_escalator_warning_24)
//            , contentDescription ="Crowd Funding Buisness",
//            modifier = Modifier.size(150.dp)
//        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp,Color.Black)
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(all=10.dp)
            ) {
                OutlinedTextField(value = email, onValueChange = { email = it },
                    label = { Text("Email Address") },
                    placeholder = { Text("abc@gmail.com") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    ),
                    isError = !isEmailvalid ,
                    trailingIcon = {
                        if(email.isNotBlank()){
                            IconButton(onClick = {email=""}) {
                                Icon(imageVector = Icons.Filled.Clear,
                                    contentDescription ="Clear Email" )
                            }
                        }
                    }
                )
                OutlinedTextField(value = Password, onValueChange = { Password = it },
                    label = { Text("Password") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    ),
                    isError = !isPasswordvalid,
                    trailingIcon = {
                        IconButton(onClick = { isPasswordremember=!isPasswordremember}) {
                            Icon(imageVector = if(isPasswordremember) Icons.Default.Visibility else Icons.Default.VisibilityOff ,
                                contentDescription = "Toggle password visibility")
                        }
                    },
                    visualTransformation = if(isPasswordremember) VisualTransformation.None else PasswordVisualTransformation()
                )
                OutlinedTextField(value = name, onValueChange = { name = it },
                    label = { Text("Name") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    )
                )
                OutlinedTextField(value = phone, onValueChange = { phone = it },
                    label = { Text("Phone") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.clearFocus() }
                    ),

                    )
                Button(onClick = {
                    auth.createUserWithEmailAndPassword(email, Password).addOnCompleteListener {
                        if (it.isSuccessful) {
                            Log.d(LoginActivity.TAG, "the user has successfully logged in")
                            val intent = Intent(context,LoginActivity::class.java)
                            context.startActivity(intent)
                        } else {
                            Log.w(LoginActivity.TAG, "The user has FAILED to log in", it.exception)
                        }
                    }
                },
                    modifier = Modifier.fillMaxWidth(),
                    colors=ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                    enabled = isEmailvalid&&isPasswordvalid
                ) {
                    Text(text = "Register",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 16.sp
                    )
                }
            }
        }
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ){
            TextButton(onClick = { /*TODO*/ }) {
                Text(
                    color = Color.Black,
                    fontStyle = FontStyle.Italic,
                    text = "              ",
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
        Button(onClick = {
            val intent= Intent(context,LoginActivity::class.java)
            context.startActivity(intent)
        },
            enabled = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp),
            colors=ButtonDefaults.buttonColors(backgroundColor = Color.White)
        )  {
            Text(
                text = "Login",
                fontWeight = FontWeight.Black,
                color=Color.Red,
                fontSize = 16.sp
            )
        }
    }
}
