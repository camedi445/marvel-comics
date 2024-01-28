package co.cmedina.marvelcomics.data.repository

import co.cmedina.marvelcomics.data.api.ComicAPI
import co.cmedina.marvelcomics.data.api.PUBLIC_KEY
import co.cmedina.marvelcomics.data.api.generateHash
import co.cmedina.marvelcomics.domain.model.Character
import co.cmedina.marvelcomics.domain.repository.ComicRepository

class ComicRepositoryImpl(
    private val comicAPI: ComicAPI
) : ComicRepository {

    override suspend fun getCharacterList(): List<Character> {
        val tsAndHashPair = generateHash()
        val ironMan = comicAPI.fetchCharacter(
            nameStartsWith = "Iron Man",
            limit = 1,
            apiKey = PUBLIC_KEY,
            hash = tsAndHashPair.second,
            ts = tsAndHashPair.first
        ).data.results.first().toDomainCharacter()
        val thor = comicAPI.fetchCharacter(
            nameStartsWith = "Thor",
            limit = 1,
            apiKey = PUBLIC_KEY,
            hash = tsAndHashPair.second,
            ts = tsAndHashPair.first
        ).data.results.first().toDomainCharacter()
        val captainAmerica = comicAPI.fetchCharacter(
            nameStartsWith = "Captain America",
            limit = 1,
            apiKey = PUBLIC_KEY,
            hash = tsAndHashPair.second,
            ts = tsAndHashPair.first
        ).data.results.first().toDomainCharacter()
        val hulk = comicAPI.fetchCharacter(
            nameStartsWith = "Hulk",
            limit = 1,
            apiKey = PUBLIC_KEY,
            hash = tsAndHashPair.second,
            ts = tsAndHashPair.first
        ).data.results.first().toDomainCharacter()
        return listOf(
            ironMan,
            captainAmerica,
            thor,
            hulk
        )
    }
}