package co.cmedina.marvelcomics.data.repository

import co.cmedina.marvelcomics.data.api.ComicAPI
import co.cmedina.marvelcomics.data.api.generateHash
import co.cmedina.marvelcomics.data.model.CharacterDTO
import co.cmedina.marvelcomics.data.model.ComicDTO
import co.cmedina.marvelcomics.domain.repository.ComicRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class ComicRepositoryImplTest {

    private val comicAPI = mockk<ComicAPI>()
    private lateinit var comicRepository: ComicRepository
    private val character = mockk<CharacterDTO> {
        every { toDomainCharacter() } returns mockk {
            every { name } returns "Iron Man"
        }
    }
    private val comic = mockk<ComicDTO> {
        every { toDomainComic() } returns mockk {
            every { title } returns "Comic Title"
        }
    }
    private val characterList = listOf(character)
    private val comicList = listOf(comic)

    @Before
    fun setup() {
        mockkStatic(::generateHash)
        every {
            generateHash()
        } returns Pair("ts", "hash")
        comicRepository = ComicRepositoryImpl(comicAPI)
    }

    @Test
    fun testGetCharacterListSuccess() = runTest {
        coEvery {
            comicAPI.fetchCharacter(
                nameStartsWith = any(),
                limit = 1,
                apiKey = any(),
                hash = "hash",
                ts = "ts"
            )
        } returns mockk {
            every { data } returns mockk {
                every { results } returns characterList
            }
        }
        val result = comicRepository.getCharacterList()
        assertTrue(result.isRight())
        assertEquals(4, result.getOrNull()?.size)
        assertEquals("Iron Man", result.getOrNull()!![0].name)
        coVerify(exactly = 4) {
            comicAPI.fetchCharacter(
                nameStartsWith = any(),
                limit = 1,
                apiKey = any(),
                hash = "hash",
                ts = "ts"
            )
            character.toDomainCharacter()
        }
    }

    @Test
    fun testGetCharacterListError() = runTest {
        coEvery {
            comicAPI.fetchCharacter(
                nameStartsWith = any(),
                limit = 1,
                apiKey = any(),
                hash = "hash",
                ts = "ts"
            )
        } throws RuntimeException(
            "Test exception"
        )
        val result = comicRepository.getCharacterList()
        assertTrue(result.isLeft())
        assertEquals(
            "Ha ocurrido un error, intenta nuevamente.",
            result.leftOrNull()?.message
        )
        coVerify {
            comicAPI.fetchCharacter(
                nameStartsWith = any(),
                limit = 1,
                apiKey = any(),
                hash = "hash",
                ts = "ts"
            )
        }
    }

    @Test
    fun testGetComicListSuccess() = runTest {
        coEvery {
            comicAPI.fetchComicListByCharacterId(
                characterId = 1,
                limit = 99,
                apiKey = any(),
                hash = "hash",
                ts = "ts"
            )
        } returns mockk {
            every { data } returns mockk {
                every { results } returns comicList
            }
        }
        val result = comicRepository.getComicList(characterId = 1)
        assertTrue(result.isRight())
        assertEquals(1, result.getOrNull()?.size)
        assertEquals("Comic Title", result.getOrNull()!![0].title)
        coVerify {
            comic.toDomainComic()
            comicAPI.fetchComicListByCharacterId(
                characterId = 1,
                limit = 99,
                apiKey = any(),
                hash = "hash",
                ts = "ts"
            )
        }
    }


    @Test
    fun testGetComicListError() = runTest {
        coEvery {
            comicAPI.fetchComicListByCharacterId(
                characterId = 1,
                limit = 99,
                apiKey = any(),
                hash = "hash",
                ts = "ts"
            )
        } throws RuntimeException("Test exception")
        val result = comicRepository.getComicList(characterId = 1)
        assertTrue(result.isLeft())
        assertEquals(
            "Ha ocurrido un error, intenta nuevamente.",
            result.leftOrNull()?.message
        )
        coVerify {
            comicAPI.fetchComicListByCharacterId(
                characterId = 1,
                limit = 99,
                apiKey = any(),
                hash = "hash",
                ts = "ts"
            )
        }
    }

    @Test
    fun testGetComicByIdSuccess() = runTest {
        coEvery {
            comicAPI.fetchComicById(
                limit = 1,
                comicId = 1,
                apiKey = any(),
                hash = "hash",
                ts = "ts"
            )
        } returns mockk {
            every { data } returns mockk {
                every { results } returns comicList
            }
        }
        val result = comicRepository.getComicById(comicId = 1)
        assertTrue(result.isRight())
        assertEquals("Comic Title", result.getOrNull()?.title)
        coVerify {
            comic.toDomainComic()
            comicAPI.fetchComicById(
                limit = 1,
                comicId = 1,
                apiKey = any(),
                hash = "hash",
                ts = "ts"
            )
        }
    }

     @Test
    fun testGetComicByIdError() = runTest {
         coEvery {
             comicAPI.fetchComicById(
                 limit = 1,
                 comicId = 1,
                 apiKey = any(),
                 hash = "hash",
                 ts = "ts"
             )
         } throws RuntimeException(
            "Test exception"
        )
        val result = comicRepository.getComicById(comicId = 1)
         assertTrue(result.isLeft())
         assertEquals(
             "Ha ocurrido un error, intenta nuevamente.",
             result.leftOrNull()?.message
         )
         coVerify {
             comicAPI.fetchComicById(
                 limit = 1,
                 comicId = 1,
                 apiKey = any(),
                 hash = "hash",
                 ts = "ts"
             )
         }
    }

    @After
    fun tearDown() {
        confirmVerified(
            comicAPI,
            character,
            comic
        )
        unmockkAll()
    }
}