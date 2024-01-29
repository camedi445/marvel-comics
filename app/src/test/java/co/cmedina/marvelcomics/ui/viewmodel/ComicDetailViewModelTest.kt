package co.cmedina.marvelcomics.ui.viewmodel

import arrow.core.Either
import co.cmedina.marvelcomics.domain.exception.MessageException
import co.cmedina.marvelcomics.domain.model.Comic
import co.cmedina.marvelcomics.domain.usecase.GetComicByIdUseCase
import co.cmedina.marvelcomics.rule.MainCoroutineRule
import co.cmedina.marvelcomics.ui.comicdetail.ComicDetailViewModel
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
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ComicDetailViewModelTest {

    private val dispatcher = UnconfinedTestDispatcher()

    @get:Rule
    val coroutineRule = MainCoroutineRule(dispatcher)

    private val getComicByIdUseCase = mockk<GetComicByIdUseCase>()
    private lateinit var comicDetailViewModel: ComicDetailViewModel
    private val expectedComic = mockk<Comic>(relaxed = true) {
        every { id } returns 1
        every { imageURL } returns "http://example.com/comic.jpg"
        every { title } returns "Comic Title"
        every { description } returns "Description"
    }


    @Before
    fun setup() {
        comicDetailViewModel = ComicDetailViewModel(
            getComicByIdUseCase,
            dispatcher
        )
    }

    @Test
    fun getComicByIdSuccess() = runTest(dispatcher) {
        coEvery { getComicByIdUseCase(1) } returns Either.Right(expectedComic)
        comicDetailViewModel.getComicById(1)
        val result = comicDetailViewModel.comicDetailState.value
        assertEquals(false, result.isLoading)
        assertEquals(expectedComic, result.comic)
        assertEquals(null, result.error)
        coVerify(exactly = 1) {
            getComicByIdUseCase(1)
        }
    }

    @Test
    fun getComicByIdError() = runTest(dispatcher) {
        val errorMessage = "Test error"
        coEvery { getComicByIdUseCase(1) } returns Either.Left(MessageException(errorMessage))
        comicDetailViewModel.getComicById(1)
        val result = comicDetailViewModel.comicDetailState.value
        assertEquals(false, result.isLoading)
        assertEquals(null, result.comic)
        assertEquals(errorMessage, result.error)
        coVerify(exactly = 1) {
            getComicByIdUseCase(1)
        }
    }

    @After
    fun tearDown() {
        confirmVerified(
            getComicByIdUseCase
        )
        unmockkAll()
    }
}