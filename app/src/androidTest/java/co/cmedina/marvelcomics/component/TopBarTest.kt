package co.cmedina.marvelcomics.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import co.cmedina.marvelcomics.ui.component.TopBar
import org.junit.Rule
import org.junit.Test

class TopBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testTopBar() {
        val title = "Sample Title"
        composeTestRule.setContent {
            MaterialTheme {
                Surface {
                    TopBar(
                        title = title,
                        onBackPressed = { }
                    )
                }
            }
        }
        composeTestRule.onNodeWithTag("CenterAlignedTopAppBar").assertExists()
        composeTestRule.onNodeWithText(title).assertExists()
        composeTestRule.onNodeWithContentDescription("Ir hacia atras").assertExists()
    }
}