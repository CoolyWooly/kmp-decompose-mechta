package kz.mechta.splashscreen

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import kz.mechta.theme.MechtaTheme
import splashscreen.presentation.SplashscreenComponent
import splashscreen.presentation.SplashscreenEffect

@Composable
fun SplashscreenPage(component: SplashscreenComponent) {
    val context = LocalContext.current
    val uiState by component.state.subscribeAsState()
    component.effect.watch {
        when (it) {
            is SplashscreenEffect.ShowToast -> {
                if (it.text.isNullOrBlank()) return@watch
                Toast.makeText(context, it.text, Toast.LENGTH_SHORT).show()
            }
        }
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MechtaTheme.colors.brand01
    ) {

    }
}