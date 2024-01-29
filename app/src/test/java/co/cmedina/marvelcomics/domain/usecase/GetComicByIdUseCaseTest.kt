package co.cmedina.marvelcomics.domain.usecase

import arrow.core.Either
import co.cmedina.marvelcomics.data.model.ComicDTO
import co.cmedina.marvelcomics.domain.exception.MessageException
import co.cmedina.marvelcomics.domain.model.Comic
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

class GetComicByIdUseCaseTest {

    private val comicRepository = mockk<ComicRepository>()
    private lateinit var getComicByIdUseCase: GetComicByIdUseCase
    private val comic = mockk<Comic> {
        every { title } returns "Comic Title"
    }

    @Before
    fun setup() {
        getComicByIdUseCase = GetComicByIdUseCase(comicRepository)
    }

    @Test
    fun testInvokeSuccess() = runTest {
        coEvery {
            comicRepository.getComicById(1)
        } returns Either.Right(comic)
        val result = getComicByIdUseCase(1)
        assertTrue(result.isRight())
        assertEquals("Comic Title", result.getOrNull()?.title)
        coVerify {
            comicRepository.getComicById(1)
            comic.title
        }
    }

    @Test
    fun testInvokeError() = runTest {
        coEvery {
            comicRepository.getComicById(1)
        } returns Either.Left(MessageException("Test error"))
        val result = getComicByIdUseCase(1)
        assertTrue(result.isLeft())
        assertEquals("Test error", result.leftOrNull()?.message)
        coVerify {
            comicRepository.getComicById(1)
        }
    }

    @After
    fun tearDown() {
        confirmVerified(
            comic,
            comicRepository
        )
        unmockkAll()
    }
}