package co.cmedina.marvelcomics.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.cmedina.marvelcomics.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    onBackPressed: () -> Unit
) {
    CenterAlignedTopAppBar(
        modifier = Modifier
            .padding(top = PADDING_BAR)
            .testTag(TEST_TAG),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Black
        ),
        title = {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(TITLE_BAR),
                text = title,
                style = TextStyle(
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = TITLE_SIZE
                ),
                maxLines = MAX_TITLE_LINES,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(
                onClick = onBackPressed
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    tint = Color.White,
                    contentDescription = stringResource(id = R.string.top_bar_back_arrow)
                )
            }
        }
    )
}

private val PADDING_BAR = 8.dp
private val TITLE_BAR = 20.dp
private val TITLE_SIZE = 24.sp
private const val MAX_TITLE_LINES = 1
private const val TEST_TAG = "CenterAlignedTopAppBar"