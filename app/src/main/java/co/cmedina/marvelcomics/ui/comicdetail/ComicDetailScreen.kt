package co.cmedina.marvelcomics.ui.comicdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import co.cmedina.marvelcomics.R
import co.cmedina.marvelcomics.domain.model.Comic
import co.cmedina.marvelcomics.ui.component.ProgressIndicator
import co.cmedina.marvelcomics.ui.component.TopBar
import co.cmedina.marvelcomics.ui.theme.MarvelComicsTheme
import coil.compose.AsyncImage
import kotlinx.coroutines.launch

@Composable
fun ComicDetailScreen(
    comicDetailViewModel: ComicDetailViewModel = hiltViewModel(),
    comicId: Int,
    onBackPressed: () -> Unit
) {
    val comicDetailState
            by comicDetailViewModel.comicDetailState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    LaunchedEffect(comicDetailState.error != null) {
        scope.launch {
            comicDetailState.error?.let { snackbarHostState.showSnackbar(it) }
        }
    }
    LaunchedEffect(Unit) {
        comicDetailViewModel.getComicById(comicId)
    }
    if (comicDetailState.isLoading) {
        ProgressIndicator()
    } else {
        comicDetailState.comic?.let {
            ComicDetailContent(
                comic = it,
                snackbarHostState = snackbarHostState,
                onBackPressed = onBackPressed
            )
        }
    }
}

@Composable
fun ComicDetailContent(
    comic: Comic,
    snackbarHostState: SnackbarHostState,
    onBackPressed: () -> Unit
) {
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopBar(title = comic.title, onBackPressed = onBackPressed)
        }
    ) { paddingValues ->
        val configuration = LocalConfiguration.current
        val imageHeight = configuration.screenHeightDp.div(IMAGE_HEIGHT_SCALE)
        LazyColumn(
            modifier = Modifier
                .background(Color.Black)
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                AsyncImage(
                    modifier = Modifier
                        .height(imageHeight.dp)
                        .fillMaxWidth(),
                    model = comic.imageURL,
                    contentScale = ContentScale.FillBounds,
                    contentDescription = stringResource(id = R.string.comic_list_image_desc)
                )
            }
            item {
                Text(
                    modifier = Modifier.padding(DESCRIPTION_PADDING),
                    text = comic.description,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = DESCRIPTION_SIZE,
                        textAlign = TextAlign.Justify,
                        fontWeight = FontWeight.W400
                    )
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ComicDetailContentPreview() {
    MarvelComicsTheme {
        ComicDetailContent(
            snackbarHostState = SnackbarHostState(),
            comic = Comic(
                id = 123,
                imageURL = "imageUrl",
                title = "title 1",
                description = "Desc 1"
            )
        ) {}
    }
}

private const val IMAGE_HEIGHT_SCALE = 2
private val DESCRIPTION_PADDING = 16.dp
private val DESCRIPTION_SIZE = 22.sp