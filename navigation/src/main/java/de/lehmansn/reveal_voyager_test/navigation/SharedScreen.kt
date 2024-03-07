package de.lehmansn.reveal_voyager_test.navigation

import cafe.adriel.voyager.core.registry.ScreenProvider
import com.svenjacobs.reveal.RevealCanvasState

sealed class SharedScreen : ScreenProvider {

    data class First(val revealCanvasState: RevealCanvasState) : SharedScreen()
}
