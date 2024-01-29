package co.cmedina.marvelcomics.data.model

import junit.framework.TestCase.assertEquals
import org.junit.Test

class CharacterDTOTest {

    @Test
    fun testToDomainCharacter() {
        val characterDTO = CharacterDTO(
            id = 1,
            thumbnail = Thumbnail("http://example.com/image", "jpg"),
            name = "John Doe"
        )
        val domainCharacter = characterDTO.toDomainCharacter()
        assertEquals(1, domainCharacter.id)
        assertEquals("https://example.com/image.jpg", domainCharacter.imageURL)
        assertEquals("John Doe", domainCharacter.name)
    }

    @Test
    fun testCharacterResponse() {
        val characterResponse = CharacterResponse(
            data = CharacterData(
                results = listOf(
                    CharacterDTO(
                        id = 1,
                        thumbnail = Thumbnail("http://example.com/image", "jpg"),
                        name = "John Doe"
                    )
                )
            )
        )
        assertEquals(1, characterResponse.data.results.size)
        assertEquals(1, characterResponse.data.results[0].id)
        assertEquals(
            "https://example.com/image.jpg",
            characterResponse.data.results[0].thumbnail.getURL()
        )
        assertEquals("John Doe", characterResponse.data.results[0].name)
    }
}