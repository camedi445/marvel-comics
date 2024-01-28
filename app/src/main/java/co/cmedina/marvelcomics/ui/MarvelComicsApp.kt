package co.cmedina.marvelcomics.ui

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import co.cmedina.marvelcomics.ui.navigation.MarvelComicsNavGraph
import co.cmedina.marvelcomics.ui.navigation.NavigationActions
import co.cmedina.marvelcomics.ui.theme.MarvelComicsTheme
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MarvelComicsApp : Application()