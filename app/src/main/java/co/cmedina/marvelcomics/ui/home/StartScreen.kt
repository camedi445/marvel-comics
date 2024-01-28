package co.cmedina.marvelcomics.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.cmedina.marvelcomics.R
import co.cmedina.marvelcomics.ui.theme.MarvelComicsTheme

@Composable
fun StartScreen(
    onNavigateToCharacterListClick: () -> Unit
) {
    StartScreenContent(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.Black),
        onNavigateToCharacterListClick = onNavigateToCharacterListClick
    )
}

@Composable
fun StartScreenContent(
    modifier: Modifier = Modifier,
    onNavigateToCharacterListClick: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InitButton(onClick = onNavigateToCharacterListClick)
    }
}

@Composable
fun InitButton(
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .padding(INIT_BUTTON_PADDING)
            .width(INIT_BUTTON_WIDTH),
        onClick = onClick
    ) {
        Text(
            text = stringResource(
                id = R.string.start_screen_init_button
            ).uppercase(),
            style = TextStyle(
                fontSize = INIT_BUTTON_TEXT_SIZE,
                fontWeight = FontWeight.W500
            )
        )
    }
}

@Composable
@Preview(showBackground = true)
fun StartScreenPreview() {
    MarvelComicsTheme {
        StartScreen {

        }
    }
}

private val INIT_BUTTON_PADDING = 8.dp
private val INIT_BUTTON_WIDTH = 192.dp
private val INIT_BUTTON_TEXT_SIZE = 24.sp