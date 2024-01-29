package co.cmedina.marvelcomics.ui.state

import co.cmedina.marvelcomics.domain.model.Comic
import co.cmedina.marvelcomics.ui.comiclist.ComicListState
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertNull
import org.junit.Test

class ComicListStateTest {

    @Test
    fun testComicListStateProperties() {
        val comicListState = ComicListState(
            isLoading = true,
            comicList = listOf(
                Comic(1, "http://example.com/comic1.jpg", "Comic Title 1", "Description 1"),
                Comic(2, "http://example.com/comic2.jpg", "Comic Title 2", "Description 2")
            ),
            error = "Test error"
        )
        assertEquals(true, comicListState.isLoading)
        assertEquals(2, comicListState.comicList.size)
        assertEquals(1, comicListState.comicList[0].id)
        assertEquals("http://example.com/comic1.jpg", comicListState.comicList[0].imageURL)
        assertEquals("Comic Title 1", comicListState.comicList[0].title)
        assertEquals("Description 1", comicListState.comicList[0].description)
        assertEquals(2, comicListState.comicList[1].id)
        assertEquals("http://example.com/comic2.jpg", comicListState.comicList[1].imageURL)
        assertEquals("Comic Title 2", comicListState.comicList[1].title)
        assertEquals("Description 2", comicListState.comicList[1].description)
        assertEquals("Test error", comicListState.error)
    }

    @Test
    fun testComicListStateEmptyList() {
        val comicListState = ComicListState(
            isLoading = false,
            comicList = emptyList(),
            error = null
        )
        assertFalse(comicListState.isLoading)
        assertEquals(0, comicListState.comicList.size)
        assertNull(comicListState.error)
    }
}