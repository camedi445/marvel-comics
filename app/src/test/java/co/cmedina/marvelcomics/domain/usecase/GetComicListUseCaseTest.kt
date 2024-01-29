package co.cmedina.marvelcomics.domain.usecase

import arrow.core.Either
import co.cmedina.marvelcomics.domain.exception.MessageException
import co.cmedina.marvelcomics.domain.model.Comic
import co.cmedina.marvelcomics.domain.repository.ComicRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class GetComicListUseCaseTest {

    private val comicRepository = mockk<ComicRepository>()
    private lateinit var getComicListUseCase: GetComicListUseCase
    private val comic = mockk<Comic> {
        every { title } returns "Comic Title"
    }
    private val comicList = listOf(comic)

    @Before
    fun setup() {
        getComicListUseCase = GetComicListUseCase(comicRepository)
    }

    @Test
    fun testInvokeSuccess() = runTest {
        coEvery {
            comicRepository.getComicList(1)
        } returns Either.Right(comicList)
        val result = getComicListUseCase(1)
        assertTrue(result.isRight())
        assertEquals(1, result.getOrNull()?.size)
        assertEquals("Comic Title", result.getOrNull()!![0].title)
        coVerify {
            comicRepository.getComicList(1)
            comic.title
        }
    }

    @Test
    fun testInvokeError() = runTest {
        coEvery {
            comicRepository.getComicList(1)
        } returns Either.Left(MessageException("Test error"))
        val result = getComicListUseCase(1)
        assertTrue(result.isLeft())
        assertEquals("Test error", result.leftOrNull()?.message)
        coVerify {
            comicRepository.getComicList(1)
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