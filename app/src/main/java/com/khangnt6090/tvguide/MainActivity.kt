package com.khangnt6090.tvguide

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import com.khangnt6090.tvguide.presentation.TvGuideViewModel
import com.khangnt6090.tvguide.presentation.TvGuideViewModelFactory
import com.khangnt6090.tvguide.ui.TvGuideScreen

class MainActivity : ComponentActivity() {
    private val viewModel: TvGuideViewModel by viewModels {
        val repository = (application as TvGuideApplication).container.tvShowRepository
        TvGuideViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme(
                colorScheme = lightColorScheme(
                    primary = Color(0xFF4355B9),
                    onPrimary = Color.White,
                    secondary = Color(0xFF00796B),
                    background = Color(0xFFF6F7FB),
                    surface = Color.White
                )
            ) {
                TvGuideScreen(viewModel = viewModel)
            }
        }
    }
}
