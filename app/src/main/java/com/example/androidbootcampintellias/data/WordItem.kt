package com.example.androidbootcampintellias.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "word", indices = [Index(value = ["meanings"], unique = true)])
data class WordItem(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "word")
    val word: String,
    @ColumnInfo(name = "phonetic")
    val phonetic: String,
    @ColumnInfo(name = "meanings")
    val meanings: List<Meaning>
)

