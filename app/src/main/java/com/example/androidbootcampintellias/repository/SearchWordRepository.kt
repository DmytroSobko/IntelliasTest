package com.example.androidbootcampintellias.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.androidbootcampintellias.network.BASE_URL
import com.example.androidbootcampintellias.network.DictionaryApiService
import com.example.androidbootcampintellias.data.Word
import com.example.androidbootcampintellias.data.WordItem
import com.example.androidbootcampintellias.room.WordRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchWordRepository(application: Application, private val viewModelScope:CoroutineScope){

    private val TAG = "SearchWordRepository"

    private val wordDao = WordRoomDatabase.getDatabase(application).wordDao()
    val wordDefinitions = MutableLiveData<List<WordItem>>()

    fun searchWord(word : String){
        viewModelScope.launch {
            try {
                val databaseWordDefinitions = wordDao.readWord(word)

                if(databaseWordDefinitions.count() > 0){
                    wordDefinitions.value = databaseWordDefinitions
                }
                else{
                    searchOnServer(word)
                }
            } catch (e: Exception) {
                Log.d(TAG, "onFailure" + e.message)
            }
        }
    }

    private fun searchOnServer(word : String){
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

        val dictionaryApiService = retrofit.create(DictionaryApiService::class.java)
        val wordData = dictionaryApiService.getWordData(word)

        wordData.enqueue(object : Callback<Word> {
            override fun onFailure(call: Call<Word>, t: Throwable) {
                Log.d(TAG, "onFailure" + t.message)
            }
            override fun onResponse(call: Call<Word>, response: Response<Word>) {
                val words = response.body()!!

                wordDefinitions.value = words

                cacheWordItem(words)
            }
        })
    }

    private fun cacheWordItem(words:Word){
        viewModelScope.launch {
            try {
                wordDao.insertWord(words)
            } catch (e: Exception) {
            }
        }
    }
}