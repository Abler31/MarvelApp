package ru.my.pet.characters

import ru.my.pet.network.NetworkLayer
import ru.my.pet.model.CharactersDTO

class CharactersRepository {
    suspend fun getCharacters(offset : Int) : CharactersDTO?{
        val request =  NetworkLayer.apiClient.getCharacters(offset = offset)
        if (request.failed){
            return null
        }
        if (!request.isSuccessful){
            return null
        }

        return request.body
    }
}