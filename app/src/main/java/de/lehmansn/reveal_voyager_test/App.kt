package de.lehmansn.reveal_voyager_test

import android.app.Application
import cafe.adriel.voyager.core.registry.ScreenRegistry
import de.lehmansn.reveal_voyager_test.navigation.SharedScreen
import de.lehmansn.reveal_voyager_test.ui.FirstScreen

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        ScreenRegistry {
            register<SharedScreen.First> { provider ->
                FirstScreen(provider.revealCanvasState)
            }
        }
    }
}