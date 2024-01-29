package co.cmedina.marvelcomics.ui.viewmodel

import arrow.core.Either
import co.cmedina.marvelcomics.domain.exception.MessageException
import co.cmedina.marvelcomics.domain.model.Character
import co.cmedina.marvelcomics.domain.usecase.GetCharacterListUseCase
import co.cmedina.marvelcomics.rule.MainCoroutineRule
import co.cmedina.marvelcomics.ui.home.CharacterListMenuViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterListMenuViewModelTest {

    private val dispatcher = UnconfinedTestDispatcher()

    @get:Rule
    val coroutineRule = MainCoroutineRule(dispatcher)

    private val getCharacterListUseCase = mockk<GetCharacterListUseCase>()
    private lateinit var characterListMenuViewModel: CharacterListMenuViewModel
    private val expectedCharacter = mockk<Character>(relaxed = true) {
        every { id } returns 1
        every { imageURL } returns "http://example.com/comic.jpg"
        every { name } returns "Iron Man"
    }
    private val characterList =
        listOf(
            expectedCharacter,
            expectedCharacter,
            expectedCharacter,
            expectedCharacter
        )

    @Test
    fun getComicListSuccess() = runTest(dispatcher) {
        coEvery {
            getCharacterListUseCase()
        } returns Either.Right(characterList)
        characterListMenuViewModel = CharacterListMenuViewModel(
            getCharacterListUseCase,
            dispatcher
        )
        val result = characterListMenuViewModel.characterListMenuState.value
        assertEquals(false, result.isLoading)
        assertEquals(expectedCharacter, result.characterList.first())
        assertEquals(4, result.characterList.size)
        assertEquals(null, result.error)
        coVerify(exactly = 1) {
            getCharacterListUseCase()
        }
    }

    @Test
    fun getComicListError() = runTest(dispatcher) {
        val errorMessage = "Test error"
        coEvery {
            getCharacterListUseCase()
        } returns Either.Left(MessageException(errorMessage))
        characterListMenuViewModel = CharacterListMenuViewModel(
            getCharacterListUseCase,
            dispatcher
        )
        val result = characterListMenuViewModel.characterListMenuState.value
        assertEquals(false, result.isLoading)
        assertEquals(0, result.characterList.size)
        assertEquals(errorMessage, result.error)
        coVerify(exactly = 1) {
            getCharacterListUseCase()
        }
    }

    @After
    fun tearDown() {
        confirmVerified(
            getCharacterListUseCase
        )
        unmockkAll()
    }
}