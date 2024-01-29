package co.cmedina.marvelcomics.data.model

import junit.framework.TestCase.assertEquals
import org.junit.Test

class ThumbnailTest {

    @Test
    fun testGetURL() {
        val thumbnail = Thumbnail(
            path = "http://example.com/image",
            extension = "jpg"
        )
        val url = thumbnail.getURL()
        assertEquals("https://example.com/image.jpg", url)
    }
}