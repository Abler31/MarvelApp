package ru.my.pet.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.my.pet.model.CharactersDTO
import ru.my.pet.utils.Constants

interface AppService {
    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @Query("ts")
        ts : String = Constants.timeSpan,
        @Query("apikey")
        apiKey : String = Constants.PUBLIC_KEY,
        @Query("hash")
        hash : String = Constants.hash(),
        @Query("offset")
        offset : String,
        @Query("orderBy")
        orderBy : String
    ) : Response<CharactersDTO>

    @GET("/v1/public/characters/{characterId}")
    suspend fun getCharacterById(
        @Path("characterId") characterId: Int,
        @Query("ts")
        ts : String = Constants.timeSpan,
        @Query("apikey")
        apiKey : String = Constants.PUBLIC_KEY,
        @Query("hash")
        hash : String = Constants.hash()
    ) : Response<CharactersDTO>

    @GET("/v1/public/characters")
    suspend fun getCharactersByName(
            @Query("ts")
            ts : String = Constants.timeSpan,
            @Query("apikey")
            apiKey : String = Constants.PUBLIC_KEY,
            @Query("hash")
            hash : String = Constants.hash(),
            @Query("offset")
            offset : String,
            @Query("nameStartsWith")
            name : String,
            @Query("orderBy")
            orderBy : String
    ) : Response<CharactersDTO>

}