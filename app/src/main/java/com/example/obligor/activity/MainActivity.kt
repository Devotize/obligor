package com.example.obligor.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.obligor.interactor.AppInteractor
import com.example.obligor.ui.destinations.Destination
import com.example.obligor.ui.screen.HomeScreen
import com.example.obligor.ui.screen.PromiserSelectionScreen
import com.example.obligor.ui.theme.ObligorTheme

class MainActivity : ComponentActivity() {

    private val appInteractor = AppInteractor()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ObligorTheme {
                // A surface container using the 'background' color from the theme
                val focusManager = LocalFocusManager.current
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = null,
                            onClick = {
                                focusManager.clearFocus(force = true)
                            }
                        ),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Destination.HomeScreen.route
                    ) {
                        composable(Destination.HomeScreen.route) {
                            HomeScreen(
                                _allPromisers = appInteractor.allPromisers,
                                _selectedPromiser = appInteractor.selectedPromiser,
                                addPromiser = appInteractor::addPromiser,
                                onPromiserCreditChange = appInteractor::selectedPromiserCreditChange,
                                onPromiserClick = {
                                    navController.navigate(Destination.PromiserSelectionScreen.route)
                                }
                            )
                        }
                        composable(Destination.PromiserSelectionScreen.route) {
                            PromiserSelectionScreen()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ObligorTheme {
        Greeting("Android")
    }
}