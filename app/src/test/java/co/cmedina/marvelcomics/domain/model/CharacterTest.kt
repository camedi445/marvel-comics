package co.cmedina.marvelcomics.domain.model

import junit.framework.TestCase.assertEquals
import org.junit.Test

class CharacterTest {

    @Test
    fun testCharacterProperties() {
        val character = Character(
            id = 1,
            imageURL = "http://example.com/image.jpg",
            name = "Iron Man"
        )
        assertEquals(1, character.id)
        assertEquals("http://example.com/image.jpg", character.imageURL)
        assertEquals("Iron Man", character.name)
    }
}