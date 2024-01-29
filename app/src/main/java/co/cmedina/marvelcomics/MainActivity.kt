package co.cmedina.marvelcomics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import co.cmedina.marvelcomics.ui.navigation.MarvelComicsNavGraph
import co.cmedina.marvelcomics.ui.theme.MarvelComicsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarvelComicsTheme {
                MarvelComicsNavGraph()
            }
        }
    }
}