package co.cmedina.marvelcomics.ui.viewmodel

import arrow.core.Either
import co.cmedina.marvelcomics.domain.exception.MessageException
import co.cmedina.marvelcomics.domain.model.Comic
import co.cmedina.marvelcomics.domain.usecase.GetComicListUseCase
import co.cmedina.marvelcomics.rule.MainCoroutineRule
import co.cmedina.marvelcomics.ui.comiclist.ComicListViewModel
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
class ComicListViewModelTest {

    private val dispatcher = UnconfinedTestDispatcher()

    @get:Rule
    val coroutineRule = MainCoroutineRule(dispatcher)

    private val getComicListUseCase = mockk<GetComicListUseCase>()
    private lateinit var comicListViewModel: ComicListViewModel
    private val expectedComic = mockk<Comic>(relaxed = true) {
        every { id } returns 1
        every { imageURL } returns "http://example.com/comic.jpg"
        every { title } returns "Comic Title"
        every { description } returns "Description"
    }
    private val comicList = listOf(expectedComic)


    @Before
    fun setup() {
        comicListViewModel = ComicListViewModel(
            getComicListUseCase,
            dispatcher
        )
    }

    @Test
    fun getComicListSuccess() = runTest(dispatcher) {
        coEvery { getComicListUseCase(1) } returns Either.Right(comicList)
        comicListViewModel.getComicList(1)
        val result = comicListViewModel.comicListState.value
        assertEquals(false, result.isLoading)
        assertEquals(expectedComic, result.comicList.first())
        assertEquals(1, result.comicList.size)
        assertEquals(null, result.error)
        coVerify(exactly = 1) {
            getComicListUseCase(1)
        }
    }

    @Test
    fun getComicListError() = runTest(dispatcher) {
        val errorMessage = "Test error"
        coEvery { getComicListUseCase(1) } returns Either.Left(MessageException(errorMessage))
        comicListViewModel.getComicList(1)
        val result = comicListViewModel.comicListState.value
        assertEquals(false, result.isLoading)
        assertEquals(0, result.comicList.size)
        assertEquals(errorMessage, result.error)
        coVerify(exactly = 1) {
            getComicListUseCase(1)
        }
    }

    @After
    fun tearDown() {
        confirmVerified(
            getComicListUseCase
        )
        unmockkAll()
    }
}