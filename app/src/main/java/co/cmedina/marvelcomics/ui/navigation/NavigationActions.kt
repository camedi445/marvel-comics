package co.cmedina.marvelcomics.ui.navigation

import androidx.navigation.NavController

class NavigationActions(navController: NavController) {
    val navigateToComicCharacterMenu: () -> Unit = {
        navController.navigate(Destinations.COMIC_CHARACTER_MENU_ROUTE)
    }
}

object Destinations {
    const val START_ROUTE = "start_route"
    const val COMIC_CHARACTER_MENU_ROUTE = "comic_character_menu_route"
    const val COMIC_CHARACTER_PARTICIPATION_LIST = "comic_character_participation_list"
    const val COMIC_CHARACTER_PARTICIPATION_DETAIL = "comic_character_participation_detail"
}