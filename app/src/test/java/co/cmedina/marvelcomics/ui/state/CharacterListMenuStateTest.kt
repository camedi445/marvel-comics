package co.cmedina.marvelcomics.ui.state

import co.cmedina.marvelcomics.ui.home.CharacterListMenuState
import co.cmedina.marvelcomics.domain.model.Character
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertNull
import org.junit.Test

class CharacterListMenuStateTest {

    @Test
    fun testCharacterListMenuStateProperties() {
        val characterListMenuState = CharacterListMenuState(
            isLoading = true,
            characterList = listOf(
                Character(1, "http://example.com/image1.jpg", "Character 1"),
                Character(2, "http://example.com/image2.jpg", "Character 2")
            ),
            error = "Test error"
        )
        assertEquals(true, characterListMenuState.isLoading)
        assertEquals(2, characterListMenuState.characterList.size)
        assertEquals(1, characterListMenuState.characterList[0].id)
        assertEquals("http://example.com/image1.jpg", characterListMenuState.characterList[0].imageURL)
        assertEquals("Character 1", characterListMenuState.characterList[0].name)
        assertEquals(2, characterListMenuState.characterList[1].id)
        assertEquals("http://example.com/image2.jpg", characterListMenuState.characterList[1].imageURL)
        assertEquals("Character 2", characterListMenuState.characterList[1].name)
        assertEquals("Test error", characterListMenuState.error)
    }

    @Test
    fun testCharacterListMenuStateEmptyList() {
        val characterListMenuState = CharacterListMenuState(
            isLoading = false,
            characterList = emptyList(),
            error = null
        )
        assertFalse(characterListMenuState.isLoading)
        assertEquals(0, characterListMenuState.characterList.size)
        assertNull(characterListMenuState.error)
    }
}