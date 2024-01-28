package co.cmedina.marvelcomics.domain.repository

import co.cmedina.marvelcomics.domain.model.Character

interface ComicRepository {

    suspend fun getCharacterList() : List<Character>
}