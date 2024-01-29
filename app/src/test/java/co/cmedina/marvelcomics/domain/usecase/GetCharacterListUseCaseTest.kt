package co.cmedina.marvelcomics.domain.usecase

import arrow.core.Either
import co.cmedina.marvelcomics.domain.exception.MessageException
import co.cmedina.marvelcomics.domain.model.Character
import co.cmedina.marvelcomics.domain.repository.ComicRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class GetCharacterListUseCaseTest {

    private val comicRepository = mockk<ComicRepository>()
    private lateinit var getCharacterListUseCase: GetCharacterListUseCase
    private val character = mockk<Character> {
        every { name } returns "Iron Man"
    }
    private val characterList = listOf(character)

    @Before
    fun setup() {
        getCharacterListUseCase = GetCharacterListUseCase(comicRepository)
    }

    @Test
    fun testInvokeSuccess() = runTest {
        coEvery {
            comicRepository.getCharacterList()
        } returns Either.Right(characterList)
        val result = getCharacterListUseCase()
        assertTrue(result.isRight())
        assertEquals(1, result.getOrNull()?.size)
        assertEquals("Iron Man", result.getOrNull()!![0].name)
        coVerify {
            comicRepository.getCharacterList()
            character.name
        }
    }

    @Test
    fun testInvokeError() = runTest {
        coEvery {
            comicRepository.getCharacterList()
        } returns Either.Left(MessageException("Test error"))
        val result = getCharacterListUseCase()
        assertTrue(result.isLeft())
        assertEquals("Test error", result.leftOrNull()?.message)
        coVerify {
            comicRepository.getCharacterList()
        }
    }

    @After
    fun tearDown() {
        confirmVerified(
            character,
            comicRepository
        )
        unmockkAll()
    }
}