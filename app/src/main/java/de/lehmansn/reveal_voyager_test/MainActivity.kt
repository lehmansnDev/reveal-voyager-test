package de.lehmansn.reveal_voyager_test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.navigator.Navigator
import com.svenjacobs.reveal.RevealCanvas
import com.svenjacobs.reveal.rememberRevealCanvasState
import de.lehmansn.reveal_voyager_test.navigation.SharedScreen
import de.lehmansn.reveal_voyager_test.ui.theme.RevealVoyagerTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RevealVoyagerTestTheme {
                val revealCanvasState = rememberRevealCanvasState()
                val firstScreen = rememberScreen(SharedScreen.First(revealCanvasState))

                RevealCanvas(
                    revealCanvasState = revealCanvasState
                ) {
                    Navigator(firstScreen)
                }
            }
        }
    }
}