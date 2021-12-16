package com.example.androidbootcampintellias.network

import com.example.androidbootcampintellias.data.Word
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

const val BASE_URL = "https://api.dictionaryapi.dev/api/v2/entries/en/"

interface DictionaryApiService {

    @GET("{word}")
    fun getWordData(@Path("word") word: String): Call<Word>
}