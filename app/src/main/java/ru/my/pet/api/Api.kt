package ru.my.pet.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.my.pet.model.CharactersDTO
import ru.my.pet.utils.Constants

interface Api {
    @GET("/v1/public/characters")
    suspend fun getCharacters(
            @Query("ts")
            ts : String = Constants.timeSpan,
            @Query("apikey")
            apiKey : String = Constants.PUBLIC_KEY,
            @Query("hash")
            hash : String = Constants.hash()
    ) : Response<CharactersDTO>

}