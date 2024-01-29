package co.cmedina.marvelcomics.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import co.cmedina.marvelcomics.ui.comicdetail.ComicDetailScreen
import co.cmedina.marvelcomics.ui.comiclist.ComicListScreen
import co.cmedina.marvelcomics.ui.home.CharacterListMenuScreen
import co.cmedina.marvelcomics.ui.home.StartScreen

@Composable
fun MarvelComicsNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = START_ROUTE,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(
            route = START_ROUTE
        ) {
            StartScreen {
                navController.navigate(COMIC_CHARACTER_MENU_ROUTE)
            }
        }
        composable(
            route = COMIC_CHARACTER_MENU_ROUTE
        ) {
            CharacterListMenuScreen { characterId, characterName ->
                navController.navigate("$COMIC_CHARACTER_LIST_ROUTE/$characterId/$characterName")
            }
        }
        composable(
            route = "$COMIC_CHARACTER_LIST_ROUTE/{$CHARACTER_ID}/{$CHARACTER_NAME}",
            arguments = listOf(
                navArgument(CHARACTER_ID) { type = NavType.IntType },
                navArgument(CHARACTER_NAME) { type = NavType.StringType },
            )
        ) {
            it.arguments?.getInt(CHARACTER_ID)?.let { characterId ->
                it.arguments?.getString(CHARACTER_NAME)?.let { characterName ->
                    ComicListScreen(
                        characterId = characterId,
                        characterName = characterName,
                        onBackPressed = {
                            navController.navigateUp()
                        },
                        onComicClick = { comicId ->
                            navController.navigate("$COMIC_CHARACTER_DETAIL_ROUTE/$comicId")
                        }
                    )
                }
            }
        }
        composable(
            route = "$COMIC_CHARACTER_DETAIL_ROUTE/{$COMIC_ID}",
            arguments = listOf(navArgument(COMIC_ID) { type = NavType.IntType })
        ) {
            it.arguments?.getInt(COMIC_ID)?.let { comicId ->
                ComicDetailScreen(comicId = comicId) {
                    navController.navigateUp()
                }
            }
        }
    }
}

private const val START_ROUTE = "start_route"
private const val COMIC_CHARACTER_MENU_ROUTE = "comic_character_menu"
private const val COMIC_CHARACTER_LIST_ROUTE = "comic_character_list"
private const val COMIC_CHARACTER_DETAIL_ROUTE = "comic_character_detail"
private const val CHARACTER_ID = "characterId"
private const val COMIC_ID = "comicId"
private const val CHARACTER_NAME = "characterName"
