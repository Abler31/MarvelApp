package ru.my.pet.characters

import ru.my.pet.network.NetworkLayer
import ru.my.pet.model.CharactersDTO

class CharactersRepository {
    suspend fun getCharacters(offset : Int, orderBy : String) : CharactersDTO?{
        val request =  NetworkLayer.apiClient.getCharacters(offset = offset, orderBy)
        if (request.failed){
            return null
        }
        if (!request.isSuccessful){
            return null
        }

        return request.body
    }
}