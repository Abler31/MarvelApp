package ru.my.pet.network

import retrofit2.Response
import retrofit2.http.GET
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
        offset : String
    ) : Response<CharactersDTO>

}