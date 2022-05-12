package ru.my.pet.network

import retrofit2.Response
import ru.my.pet.model.CharactersDTO
import java.lang.Exception

class ApiClient(private val appService: AppService) {

    suspend fun getCharacters(offset : Int, orderBy : String): SimpleResponse<CharactersDTO>{
        return safeApiCall { appService.getCharacters(offset = offset.toString(), orderBy = orderBy) }
    }

    suspend fun getCharactersByName(offset: Int, name : String, orderBy : String) : SimpleResponse<CharactersDTO>{
        return safeApiCall { appService.getCharactersByName(offset = offset.toString(), name = name, orderBy = orderBy)}
    }

    suspend fun getCharacterByName(offset: Int, name : String, orderBy : String) : SimpleResponse<CharactersDTO>{
        return safeApiCall { appService.getCharactersByName(offset = offset.toString(), name = name, orderBy = orderBy)}
    }

    suspend fun getCharacterById(characterId : Int) : SimpleResponse<CharactersDTO>{
        return safeApiCall { appService.getCharacterById(characterId = characterId)}
    }

    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): SimpleResponse<T> {
        return try {
            SimpleResponse.success(apiCall.invoke())
        } catch (e: Exception){
            SimpleResponse.failure(e)
        }
    }
}
