package ru.my.pet.repository

import android.util.Log
import retrofit2.Response
import ru.my.pet.api.RetrofitInstance
import ru.my.pet.model.CharactersDTO

class Repository {
    suspend fun getCharacters() : CharactersDTO?{
        val request =  RetrofitInstance.api.getCharacters()
        if (request.isSuccessful){
            Log.d("network", request.body().toString())
            return request.body()
        }

        Log.d("network", request.errorBody().toString())
        return null
    }
}