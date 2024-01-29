package co.cmedina.marvelcomics.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import co.cmedina.marvelcomics.ui.component.ProgressIndicator
import org.junit.Rule
import org.junit.Test

class ProgressIndicatorTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testProgressIndicator() {
        composeTestRule.setContent {
            MaterialTheme {
                Surface {
                    ProgressIndicator()
                }
            }
        }
        composeTestRule.onNodeWithTag("CircularProgressIndicator").assertExists()
    }
}