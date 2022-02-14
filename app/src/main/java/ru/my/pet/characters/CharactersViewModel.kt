package ru.my.pet.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn

class CharactersViewModel : ViewModel() {
    private val repository = CharactersRepository()
    private var currentUserSearch : String = ""
    private var pagingSource : CharactersPagingSource? = null
        get() {
            if (field == null || field?.invalid == true){
                field = CharactersPagingSource(repository, currentUserSearch)
            }
            return field
        }
    val flow = Pager(
        // Configure how data is loaded by passing additional properties to
        // PagingConfig, such as prefetchDistance.
        PagingConfig(pageSize = 20)
    ) {
        pagingSource!!
    }.flow
        .cachedIn(viewModelScope)

    //принимаем поисковый запрос
    fun submitQuery(userSearch: String) {
        currentUserSearch = userSearch
        pagingSource?.invalidate()
    }
}