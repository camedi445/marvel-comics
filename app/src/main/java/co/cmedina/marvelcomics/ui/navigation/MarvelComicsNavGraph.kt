package co.cmedina.marvelcomics.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.cmedina.marvelcomics.ui.home.CharacterListMenuScreen
import co.cmedina.marvelcomics.ui.home.StartScreen

@Composable
fun MarvelComicsNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Destinations.START_ROUTE,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(
            route = Destinations.START_ROUTE
        ) {
            StartScreen {
                navController.navigate(Destinations.COMIC_CHARACTER_MENU_ROUTE)
            }
        }
        composable(
            route = Destinations.COMIC_CHARACTER_MENU_ROUTE
        ) {
            CharacterListMenuScreen()
        }
    }
}
