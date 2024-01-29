package co.cmedina.marvelcomics.data.model

import junit.framework.TestCase.assertEquals
import org.junit.Test

class ComicDTOTest {

    @Test
    fun testToDomainComic() {
        val comicDTO = ComicDTO(
            id = 1,
            thumbnail = Thumbnail("http://example.com/image", "jpg"),
            title = "Comic Title",
            description = "Description of the comic"
        )
        val domainComic = comicDTO.toDomainComic()
        assertEquals(1, domainComic.id)
        assertEquals("https://example.com/image.jpg", domainComic.imageURL)
        assertEquals("Comic Title", domainComic.title)
        assertEquals("Description of the comic", domainComic.description)
    }

    @Test
    fun testComicResponse() {
        val comicResponse = ComicResponse(
            data = ComicData(
                results = listOf(
                    ComicDTO(
                        id = 1,
                        thumbnail = Thumbnail("http://example.com/image", "jpg"),
                        title = "Comic Title",
                        description = "Description of the comic"
                    )
                )
            )
        )

        assertEquals(1, comicResponse.data.results.size)
        assertEquals(1, comicResponse.data.results[0].id)
        assertEquals(
            "https://example.com/image.jpg",
            comicResponse.data.results[0].thumbnail.getURL()
        )
        assertEquals("Comic Title", comicResponse.data.results[0].title)
        assertEquals("Description of the comic", comicResponse.data.results[0].description)
    }
}