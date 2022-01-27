package ru.my.pet.screens.viewpager.first

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.my.pet.repository.Repository

class FirstViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FirstViewModel(repository) as T
    }

}