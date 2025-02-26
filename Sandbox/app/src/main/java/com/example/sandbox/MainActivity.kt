package com.example.sandbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Insert
import androidx.room.Room
import com.example.sandbox.ui.theme.SandboxTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    private lateinit var db: AppDatabase
    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = Room.databaseBuilder(
            context = applicationContext,
            AppDatabase::class.java, "cool-db"
        ).build()
        setContent {
            SandboxTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        db = db,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, db: AppDatabase, modifier: Modifier = Modifier) {
    val coroutineScope = rememberCoroutineScope()

    Button(onClick = {
        // Launch a coroutine to perform the database operation off the main thread
        coroutineScope.launch {
            withContext(Dispatchers.IO) {
                db.userDao().insertAll(User(uid = 1, firstName = "John", lastName = "Doe"))
            }
        }    }) {
        Text(text = "Add User")
    }
}