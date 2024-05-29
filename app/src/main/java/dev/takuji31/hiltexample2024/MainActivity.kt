package dev.takuji31.hiltexample2024

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dagger.hilt.android.AndroidEntryPoint
import dev.takuji31.hiltexample2024.ui.theme.HiltExample2024Theme
import dev.takuji31.model.UserId

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val currentUserId by viewModel.currentUserId.collectAsState()
            val currentUserCount by viewModel.currentUserCount.collectAsState()
            val allUserCount by viewModel.allUserCount.collectAsState()
            HiltExample2024Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Content(
                        currentUserId = currentUserId,
                        currentUserCount = currentUserCount,
                        allUserCount = allUserCount,
                        onClickButton = viewModel::incrementCount,
                        onClickResetButton = {
                            viewModel.resetUser()
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Content(
    currentUserId: UserId,
    currentUserCount: Int,
    allUserCount: Int,
    onClickButton: () -> Unit,
    onClickResetButton: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(modifier) {
        Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Text(text = "User ID: ${currentUserId.rawValue}")
            Text(text = "current user clicked: $currentUserCount")
            Text(text = "total: $allUserCount")
            Button(onClick = onClickButton) {
                Text("Click!")
            }
            Button(onClick = onClickResetButton) {
                Text("Reset User")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HiltExample2024Theme {
        Content(UserId(1L), 1000, 1000, {}, {})
    }
}