package com.example.androidbootcampintellias.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidbootcampintellias.data.WordItem

@Dao
interface WordDao {
    @Query("SELECT * FROM word WHERE word.word = :word")
    suspend fun readWord(word: String): List<WordItem>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWord(list: ArrayList<WordItem>)
}