package ru.my.pet.characters

import android.util.Log
import android.widget.Toast
import ru.my.pet.network.NetworkLayer
import ru.my.pet.model.CharactersDTO
import ru.my.pet.network.SimpleResponse

class CharactersRepository {
    suspend fun getCharacters(offset : Int, orderBy : String) : SimpleResponse<CharactersDTO> {
        val request =  NetworkLayer.apiClient.getCharacters(offset = offset, orderBy)

        return request
    }

    suspend fun getCharacterById(characterId : Int) : CharactersDTO? {
        val request =  NetworkLayer.apiClient.getCharacterById(characterId = characterId)
        if (request.failed || !request.isSuccessful){
            Log.d("web", "request failed")
            return null
        }
        return request.body
    }

    suspend fun getCharactersByName(offset: Int, name : String, orderBy : String) : SimpleResponse<CharactersDTO> {
        val response =  NetworkLayer.apiClient.getCharactersByName(offset = offset, name = name, orderBy = orderBy)
        return response
    }

    suspend fun getCharacterByName(offset: Int, name : String, orderBy : String) : SimpleResponse<CharactersDTO> {
        val response =  NetworkLayer.apiClient.getCharacterByName(offset = offset, name = name, orderBy = orderBy)
        return response
    }
}