package co.cmedina.marvelcomics.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GridWithTitleList(
    title: String,
    fixedLazyGridSize: Int,
    modifier: Modifier = Modifier,
    content: LazyGridScope.() -> Unit
) {
    Spacer(modifier = modifier)
    Text(
        text = title,
        modifier = Modifier
            .padding(
                start = TITLE_PADDING,
                end = TITLE_PADDING,
                bottom = TITLE_PADDING
            )
            .fillMaxWidth(),
        style = TextStyle(
            color = Color.White,
            fontWeight = FontWeight.W700,
            fontSize = TITLE_SIZE
        )
    )
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .padding(LAZY_GRID_PADDING),
        columns = GridCells.Fixed(fixedLazyGridSize),
        content = content
    )
}

private val TITLE_PADDING = 16.dp
private val TITLE_SIZE = 22.sp
private val LAZY_GRID_PADDING = 8.dp