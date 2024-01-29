package co.cmedina.marvelcomics.domain.repository

import arrow.core.Either
import co.cmedina.marvelcomics.domain.exception.MessageException
import co.cmedina.marvelcomics.domain.model.Character
import co.cmedina.marvelcomics.domain.model.Comic

interface ComicRepository {
    suspend fun getCharacterList(): Either<MessageException, List<Character>>
    suspend fun getComicList(characterId: Int): Either<MessageException, List<Comic>>
    suspend fun getComicById(comicId: Int): Either<MessageException, Comic>
}