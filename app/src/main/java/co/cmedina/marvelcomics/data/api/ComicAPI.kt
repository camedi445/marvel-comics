package co.cmedina.marvelcomics.data.api

import co.cmedina.marvelcomics.data.model.CharacterResponse
import co.cmedina.marvelcomics.data.model.ComicResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ComicAPI {

    @GET("characters")
    suspend fun fetchCharacter(
        @Query("nameStartsWith") nameStartsWith: String,
        @Query("limit") limit: Int = CHARACTER_LIMIT_CALL,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: String
    ): CharacterResponse

    @GET("characters/{characterId}/comics")
    suspend fun fetchComicListByCharacterId(
        @Path("characterId") characterId: Int,
        @Query("limit") limit: Int = COMIC_LIMIT_CALL,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: String
    ): ComicResponse

    @GET("comics/{comicId}")
    suspend fun fetchComicById(
        @Path("comicId") comicId: Int,
        @Query("limit") limit: Int = CHARACTER_LIMIT_CALL,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: String
    ): ComicResponse
}

private const val CHARACTER_LIMIT_CALL = 1
private const val COMIC_LIMIT_CALL = 99