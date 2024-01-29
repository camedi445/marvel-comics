package co.cmedina.marvelcomics.ui.state

import co.cmedina.marvelcomics.domain.model.Comic
import co.cmedina.marvelcomics.ui.comicdetail.ComicDetailState
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertNull
import org.junit.Test

class ComicDetailStateTest {

    @Test
    fun testComicDetailStateProperties() {
        val comicDetailState = ComicDetailState(
            isLoading = true,
            comic = Comic(
                1,
                "http://example.com/comic.jpg",
                "Comic Title",
                "Description"
            ),
            error = "Test error"
        )
        assertEquals(true, comicDetailState.isLoading)
        assertEquals(1, comicDetailState.comic?.id)
        assertEquals("http://example.com/comic.jpg", comicDetailState.comic?.imageURL)
        assertEquals("Comic Title", comicDetailState.comic?.title)
        assertEquals("Description", comicDetailState.comic?.description)
        assertEquals("Test error", comicDetailState.error)
    }

    @Test
    fun testComicDetailStateNullComic() {
        val comicDetailState = ComicDetailState(
            isLoading = false,
            comic = null,
            error = null
        )
        assertFalse(comicDetailState.isLoading)
        assertNull(comicDetailState.comic)
        assertNull(comicDetailState.error)
    }
}