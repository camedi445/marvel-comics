package co.cmedina.marvelcomics.domain.model

import junit.framework.TestCase.assertEquals
import org.junit.Test

class ComicTest {
    @Test
    fun testComicProperties() {
        val comic = Comic(
            id = 1,
            imageURL = "http://example.com/comic.jpg",
            title = "Comic Title",
            description = "Description of the comic"
        )
        assertEquals(1, comic.id)
        assertEquals("http://example.com/comic.jpg", comic.imageURL)
        assertEquals("Comic Title", comic.title)
        assertEquals("Description of the comic", comic.description)
    }
}