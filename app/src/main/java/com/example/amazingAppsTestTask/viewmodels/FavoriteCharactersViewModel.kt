package com.example.amazingAppsTestTask.viewmodels

import androidx.lifecycle.*
import com.example.amazingAppsTestTask.model.database.Character
import com.example.amazingAppsTestTask.model.database.CharacterDao
import kotlinx.coroutines.launch

class FavoriteCharactersViewModel(
    private val characterDao: CharacterDao
) : ViewModel() {

    val characters: LiveData<List<Character>> = characterDao.getAll()


    fun deleteCharacter(character: Character) {
        viewModelScope.launch {
            characterDao.delete(character)
        }
    }


}

/**
 * Factory class to instantiate the [ViewModel] instance.
 */
class FavoriteCharactersViewModelFactory(
    private val characterDao: CharacterDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteCharactersViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavoriteCharactersViewModel(characterDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}