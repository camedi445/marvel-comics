package co.cmedina.marvelcomics.data.api

import co.cmedina.marvelcomics.data.model.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ComicAPI {

    @GET("characters")
    suspend fun fetchCharacter(
        @Query("nameStartsWith") nameStartsWith: String,
        @Query("limit") limit: Int,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: String
    ): CharacterResponse
}