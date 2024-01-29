package co.cmedina.marvelcomics.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.unit.dp
import co.cmedina.marvelcomics.ui.component.GridWithTitleList
import org.junit.Rule
import org.junit.Test

class GridWithTitleListTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testGridWithTitleList() {
        composeTestRule.setContent {
            MaterialTheme {
                Surface {
                    GridWithTitleList(
                        title = "Sample Title",
                        fixedLazyGridSize = 2,
                        modifier = Modifier,
                        content = {
                            items(10) { index ->
                                Text(
                                    text = "Item $index",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    color = Color.White
                                )
                            }
                        }
                    )
                }
            }
        }
        composeTestRule.onNodeWithText("Sample Title").assertExists()
        composeTestRule.onNodeWithTag("LazyVerticalGrid").assertExists()
        for (index in 0 until 10) {
            composeTestRule.onNodeWithText("Item $index").assertExists()
        }
    }
}