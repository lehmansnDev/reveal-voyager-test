package de.lehmansn.reveal_voyager_test.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.svenjacobs.reveal.Reveal
import com.svenjacobs.reveal.RevealCanvasState
import com.svenjacobs.reveal.RevealOverlayArrangement
import com.svenjacobs.reveal.rememberRevealState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
class SecondScreen(
    private val revealCanvasState: RevealCanvasState
) : Screen {

    @Composable
    override fun Content() {
        val revealState = rememberRevealState()
        val coroutineScope = rememberCoroutineScope()
        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(Unit) {
            delay(200L)
            if (!revealState.isVisible) {
                revealState.reveal(Keys.Second)
            }
        }

        Reveal(
            revealCanvasState = revealCanvasState,
            revealState = revealState,
            overlayContent = { key ->
                when (key) {
                    Keys.Second -> {
                        Surface(
                            modifier = Modifier
                                .align(horizontalArrangement = RevealOverlayArrangement.Start)
                                .padding(8.dp),
                            shape = RoundedCornerShape(4.dp),
                            color = Color.White,
                        ) {
                            Text("This is an explanation")
                        }
                    }
                }
            },
            onOverlayClick = { coroutineScope.launch { revealState.hide() } },
            onRevealableClick = { coroutineScope.launch { revealState.hide() } },
        ) {
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        title = { Text(text = "Second") },
                        navigationIcon = {
                            IconButton(onClick = { navigator.pop() }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
                                    contentDescription = null
                                )
                            }
                        }
                    )
                },
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer
            ) { paddingValues ->
                Box(
                    Modifier
                        .fillMaxSize()
                        .padding(paddingValues = paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Hello World!",
                        modifier = Modifier.revealable(Keys.Second)
                    )
                }
            }
        }
    }
}