package ru.my.pet.characters.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.my.pet.characters.CharactersRepository
import ru.my.pet.model.CharactersDTO

class CharacterDetailViewModel : ViewModel(){

    private val repository = CharactersRepository()

    private val _characterByIdLiveData = MutableLiveData<CharactersDTO>()
    val characterByIdLiveData: LiveData<CharactersDTO> = _characterByIdLiveData

    fun fetchCharacter(characterId: Int) = viewModelScope.launch {
        val character = repository.getCharacterById(characterId)
        _characterByIdLiveData.postValue(character)
    }


}