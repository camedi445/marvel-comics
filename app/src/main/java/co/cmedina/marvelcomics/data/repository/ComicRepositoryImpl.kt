package co.cmedina.marvelcomics.data.repository

import arrow.core.Either
import co.cmedina.marvelcomics.data.api.ComicAPI
import co.cmedina.marvelcomics.data.api.PUBLIC_KEY
import co.cmedina.marvelcomics.data.api.generateHash
import co.cmedina.marvelcomics.domain.exception.MessageException
import co.cmedina.marvelcomics.domain.model.Character
import co.cmedina.marvelcomics.domain.model.Comic
import co.cmedina.marvelcomics.domain.repository.ComicRepository

class ComicRepositoryImpl(
    private val comicAPI: ComicAPI
) : ComicRepository {

    override suspend fun getCharacterList(): Either<MessageException, List<Character>> {
        return try {
            val tsAndHashPair = generateHash()
            val ironMan = comicAPI.fetchCharacter(
                nameStartsWith = IRON_MAN_NAME,
                apiKey = PUBLIC_KEY,
                hash = tsAndHashPair.second,
                ts = tsAndHashPair.first
            ).data.results.first().toDomainCharacter()
            val thor = comicAPI.fetchCharacter(
                nameStartsWith = THOR_NAME,
                apiKey = PUBLIC_KEY,
                hash = tsAndHashPair.second,
                ts = tsAndHashPair.first
            ).data.results.first().toDomainCharacter()
            val captainAmerica = comicAPI.fetchCharacter(
                nameStartsWith = CAPTAIN_AMERICA_NAME,
                apiKey = PUBLIC_KEY,
                hash = tsAndHashPair.second,
                ts = tsAndHashPair.first
            ).data.results.first().toDomainCharacter()
            val hulk = comicAPI.fetchCharacter(
                nameStartsWith = HULK_NAME,
                apiKey = PUBLIC_KEY,
                hash = tsAndHashPair.second,
                ts = tsAndHashPair.first
            ).data.results.first().toDomainCharacter()
            Either.Right(
                listOf(
                    ironMan,
                    captainAmerica,
                    thor,
                    hulk
                )
            )
        } catch (exception: Exception) {
            Either.Left(MessageException(GENERAL_ERROR_MESSAGE))
        }
    }

    override suspend fun getComicList(characterId: Int): Either<MessageException, List<Comic>> {
        return try {
            val tsAndHashPair = generateHash()
            Either.Right(
                comicAPI.fetchComicListByCharacterId(
                    characterId = characterId,
                    apiKey = PUBLIC_KEY,
                    hash = tsAndHashPair.second,
                    ts = tsAndHashPair.first
                ).data.results.map { it.toDomainComic() }
            )
        } catch (exception: Exception) {
            Either.Left(MessageException(GENERAL_ERROR_MESSAGE))
        }
    }

    override suspend fun getComicById(comicId: Int): Either<MessageException, Comic> {
        return try {
            val tsAndHashPair = generateHash()
            Either.Right(
                comicAPI.fetchComicById(
                    comicId = comicId,
                    apiKey = PUBLIC_KEY,
                    hash = tsAndHashPair.second,
                    ts = tsAndHashPair.first
                ).data.results.first().toDomainComic()
            )
        } catch (exception: Exception) {
            Either.Left(MessageException(GENERAL_ERROR_MESSAGE))
        }
    }
}

private const val HULK_NAME = "Hulk"
private const val CAPTAIN_AMERICA_NAME = "Captain America"
private const val THOR_NAME = "Thor"
private const val IRON_MAN_NAME = "Iron Man"
private const val GENERAL_ERROR_MESSAGE = "Ha ocurrido un error, intenta nuevamente."