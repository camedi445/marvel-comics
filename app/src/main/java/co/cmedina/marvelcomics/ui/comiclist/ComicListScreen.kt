package co.cmedina.marvelcomics.ui.comiclist

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
import co.cmedina.marvelcomics.domain.model.Comic
import co.cmedina.marvelcomics.ui.component.GridWithTitleList
import co.cmedina.marvelcomics.ui.component.ProgressIndicator
import co.cmedina.marvelcomics.ui.component.TopBar
import co.cmedina.marvelcomics.ui.theme.MarvelComicsTheme
import coil.compose.AsyncImage
import kotlinx.coroutines.launch

@Composable
fun ComicListScreen(
    characterId: Int,
    characterName: String,
    comicListViewModel: ComicListViewModel = hiltViewModel(),
    onComicClick: (comicId: Int) -> Unit,
    onBackPressed: () -> Unit
) {
    val comicListState
            by comicListViewModel.comicListState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    LaunchedEffect(comicListState.error != null) {
        scope.launch {
            comicListState.error?.let { snackbarHostState.showSnackbar(it) }
        }
    }
    LaunchedEffect(Unit) {
        comicListViewModel.getComicList(characterId)
    }
    if (comicListState.isLoading) {
        ProgressIndicator()
    } else {
        ComicListContent(
            comicList = comicListState.comicList,
            characterName = characterName,
            snackbarHostState = snackbarHostState,
            onComicClick = onComicClick,
            onBackPressed = onBackPressed
        )
    }
}

@Composable
fun ComicListContent(
    comicList: List<Comic>,
    characterName: String,
    snackbarHostState: SnackbarHostState,
    onComicClick: (comicId: Int) -> Unit,
    onBackPressed: () -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(title = characterName, onBackPressed = onBackPressed)
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .background(Color.Black)
                .padding(paddingValues)
        ) {
            GridWithTitleList(
                title = stringResource(id = R.string.comic_list_title),
                fixedLazyGridSize = GRID_FIXED_SIZE,
                modifier = Modifier.size(TOP_SPACE_SIZE)
            ) {
                items(comicList) { comic ->
                    ComicItem(
                        imageURL = comic.imageURL,
                        comicId = comic.id,
                        onComicClick = onComicClick
                    )
                }
            }
        }
    }
}

@Composable
fun ComicItem(
    modifier: Modifier = Modifier,
    imageURL: String,
    comicId: Int,
    onComicClick: (comicId: Int) -> Unit
) {
    val configuration = LocalConfiguration.current
    val cardHeight = configuration.screenHeightDp.div(CARD_HEIGHT_SCALE)
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(cardHeight.dp)
            .padding(CARD_PADDING)
            .clip(MaterialTheme.shapes.extraSmall)
            .clickable {
                onComicClick(comicId)
            }
    ) {
        AsyncImage(
            model = imageURL,
            contentScale = ContentScale.FillBounds,
            contentDescription = stringResource(id = R.string.comic_list_image_desc)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ComicListContentPreview() {
    MarvelComicsTheme {
        ComicListContent(
            characterName = "Iron Man",
            snackbarHostState = SnackbarHostState(),
            onComicClick = {},
            comicList = listOf(
                Comic(
                    id = 123,
                    imageURL = "imageUrl",
                    title = "title 1",
                    description = "Desc 1"
                )
            ),
        ) {}
    }
}


private const val GRID_FIXED_SIZE = 4
private val CARD_PADDING = 4.dp
private const val CARD_HEIGHT_SCALE = 4.75
private val TOP_SPACE_SIZE = 24.dp