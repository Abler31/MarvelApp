package ru.my.pet.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn

class CharactersViewModel : ViewModel() {
    private val repository = CharactersRepository()
    val flow = Pager(
        // Configure how data is loaded by passing additional properties to
        // PagingConfig, such as prefetchDistance.
        PagingConfig(pageSize = 20)
    ) {
        CharactersPagingSource(repository)
    }.flow
        .cachedIn(viewModelScope)
}