package com.example.coupleexpenses

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.coupleexpenses.ui.theme.CoupleExpensesTheme
import androidx.compose.material3.Text as Text1
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoupleExpensesTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    var name by remember { mutableStateOf("") }
    var greetingMessage by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text1("Enter your name") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = { greetingMessage = "Hello $name!" }) {
                    Text("Enter")
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (greetingMessage.isNotEmpty()) {
                    Text(
                        text = greetingMessage,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    CoupleExpensesTheme {
        MainScreen()
    }
}