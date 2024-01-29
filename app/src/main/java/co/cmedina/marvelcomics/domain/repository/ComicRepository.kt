package co.cmedina.marvelcomics.domain.repository

import co.cmedina.marvelcomics.domain.model.Character
import co.cmedina.marvelcomics.domain.model.Comic

interface ComicRepository {
    suspend fun getCharacterList(): List<Character>
    suspend fun getComicList(characterId: Int): List<Comic>

    suspend fun getComicById(comicId: Int): Comic
}