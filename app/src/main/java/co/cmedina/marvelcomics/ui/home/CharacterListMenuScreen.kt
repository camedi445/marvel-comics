package co.cmedina.marvelcomics.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import co.cmedina.marvelcomics.R
import co.cmedina.marvelcomics.domain.model.Character
import co.cmedina.marvelcomics.ui.component.GridWithTitleList
import co.cmedina.marvelcomics.ui.component.ProgressIndicator
import co.cmedina.marvelcomics.ui.theme.MarvelComicsTheme
import coil.compose.AsyncImage
import kotlinx.coroutines.launch

@Composable
fun CharacterListMenuScreen(
    characterListMenuViewModel: CharacterListMenuViewModel = hiltViewModel(),
    onCharacterClick: (characterId: Int, characterName: String) -> Unit
) {
    val characterListMenuState
            by characterListMenuViewModel.characterListMenuState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    LaunchedEffect(characterListMenuState.error != null) {
        scope.launch {
            characterListMenuState.error?.let { snackbarHostState.showSnackbar(it) }
        }
    }

    if (characterListMenuState.isLoading) {
        ProgressIndicator()
    } else {
        CharacterListMenuContent(
            characterListMenuState.characterList,
            snackbarHostState,
            onCharacterClick = onCharacterClick
        )
    }
}


@Composable
fun CharacterListMenuContent(
    characterList: List<Character>,
    snackbarHostState: SnackbarHostState,
    onCharacterClick: (characterId: Int, characterName: String) -> Unit
) {
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .background(Color.Black)
                .padding(paddingValues)
        ) {
            GridWithTitleList(
                title = stringResource(id = R.string.character_list_menu_title),
                fixedLazyGridSize = GRID_FIXED_SIZE,
                modifier = Modifier.size(TOP_SPACE_SIZE)
            ) {
                items(characterList) { character ->
                    CharacterItem(
                        imageURL = character.imageURL,
                        characterId = character.id,
                        characterName = character.name,
                        onCharacterClick = onCharacterClick
                    )
                }
            }
        }
    }
}

@Composable
fun CharacterItem(
    modifier: Modifier = Modifier,
    imageURL: String,
    characterId: Int,
    characterName: String,
    onCharacterClick: (characterId: Int, characterName: String) -> Unit
) {
    val configuration = LocalConfiguration.current
    val cardHeight = configuration.screenHeightDp.div(CARD_HEIGHT_SCALE)
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(cardHeight.dp)
            .padding(CARD_PADDING)
            .clip(MaterialTheme.shapes.small)
            .clickable {
                onCharacterClick(characterId, characterName)
            }
    ) {
        AsyncImage(
            model = imageURL,
            contentScale = ContentScale.FillBounds,
            contentDescription = stringResource(id = R.string.character_list_menu_desc)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CharacterListMenuScreenPreview() {
    MarvelComicsTheme {
        CharacterListMenuContent(
            characterList = listOf(
                Character(
                    id = 1,
                    imageURL = "imageUrl",
                    name = "Iron Man"
                )
            ),
            snackbarHostState = SnackbarHostState(),
            onCharacterClick = { _, _ -> }
        )
    }
}

private const val GRID_FIXED_SIZE = 2
private val CARD_PADDING = 4.dp
private const val CARD_HEIGHT_SCALE = 2.5
private val TOP_SPACE_SIZE = 48.dp