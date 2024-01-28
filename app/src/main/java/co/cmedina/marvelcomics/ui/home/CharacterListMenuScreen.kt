package co.cmedina.marvelcomics.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import co.cmedina.marvelcomics.domain.model.Character
import coil.compose.AsyncImage

@Composable
fun CharacterListMenuScreen(
    characterListMenuViewModel: CharacterListMenuViewModel = hiltViewModel()
) {
    val characterListMenuState
        by characterListMenuViewModel.characterListMenuState.collectAsStateWithLifecycle()

    if (characterListMenuState.isLoading) {
        Column(
            modifier = Modifier.background(Color.Black).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement =  Arrangement.Center
        ) {
            CircularProgressIndicator()
        }

    } else {
        CharacterListMenuContent(
            characterListMenuState.characterList
        )
    }
}


@Composable
fun CharacterListMenuContent(
    characterList: List<Character>
) {
    Column(
        modifier = Modifier.background(Color.Black)
    ) {
        Spacer(modifier = Modifier.size(48.dp))
        Text(
            text = "Selecciona un personaje",
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                )
                .fillMaxWidth(),
            style = TextStyle(
                color = Color.White,
                fontWeight = FontWeight.W700,
                fontSize = 24.sp
            )
        )
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            columns = GridCells.Fixed(2)

        ) {
            items(characterList) { character ->
                CharacterItem(
                    imageURL = character.imageURL
                )
            }
        }
    }
}

@Composable
fun CharacterItem(
    modifier: Modifier = Modifier,
    imageURL: String
) {
    val configuration = LocalConfiguration.current
    val cardHeight = configuration.screenHeightDp.div(2.375)
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(cardHeight.dp)
            .padding(4.dp)
            .clip(MaterialTheme.shapes.small)
            .clickable { /* Handle card click */ }
    ) {
        AsyncImage(
            model = imageURL,
            contentScale = ContentScale.FillBounds,

            // TODO placeholder = painterResource(id = R.drawable.sudoimage),
            // TODO error = painterResource(id = R.drawable.sudoimage),
            contentDescription = null //"The delasign logo",
        )
    }
}

/*@Composable
@Preview(showBackground = true)
fun CharacterListMenuScreenPreview() {
    MarvelComicsTheme {
        CharacterListMenuContent(

        )
    }
}*/ // TODO fix it