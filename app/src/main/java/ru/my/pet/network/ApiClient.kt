package ru.my.pet.network

import retrofit2.Response
import ru.my.pet.model.CharactersDTO
import java.lang.Exception

class ApiClient(private val appService: AppService) {

    suspend fun getCharacters(offset : Int): SimpleResponse<CharactersDTO>{
        return safeApiCall { appService.getCharacters(offset = offset.toString()) }
    }

    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): SimpleResponse<T> {
        return try {
            SimpleResponse.success(apiCall.invoke())
        } catch (e: Exception){
            SimpleResponse.failure(e)
        }
    }
}
