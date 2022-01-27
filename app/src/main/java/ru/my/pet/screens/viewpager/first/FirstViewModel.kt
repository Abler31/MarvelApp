package ru.my.pet.screens.viewpager.first

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import ru.my.pet.model.CharactersDTO
import ru.my.pet.model.Result
import ru.my.pet.repository.Repository

class FirstViewModel(private val repository: Repository) : ViewModel() {
    val characterList: MutableLiveData<List<Result>> = MutableLiveData()
    fun getCharacters(){
        viewModelScope.launch {
            val response = repository.getCharacters()?.data?.results
            characterList.value = response

        }
    }
}