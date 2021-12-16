package com.example.androidbootcampintellias.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.androidbootcampintellias.data.WordItem
import com.example.androidbootcampintellias.repository.SearchWordRepository

class SearchWordViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = SearchWordRepository(application, viewModelScope)
    val wordDefinitionsList : LiveData<List<WordItem>>

    init {
        this.wordDefinitionsList = repository.wordDefinitions
    }

    fun searchWord(word : String){
        repository.searchWord(word)
    }
}