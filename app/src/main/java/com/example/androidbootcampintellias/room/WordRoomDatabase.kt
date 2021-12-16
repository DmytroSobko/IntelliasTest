package com.example.androidbootcampintellias.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.androidbootcampintellias.data.WordItem
import com.example.androidbootcampintellias.room.converters.MeaningsTypeConverter

const val DATABASE_FILE_NAME = "word"

@Database(entities = [WordItem::class], version = 1, exportSchema = false)
@TypeConverters(MeaningsTypeConverter::class)
abstract class WordRoomDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao

    companion object {
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        fun getDatabase(context: Context): WordRoomDatabase {
            if (INSTANCE == null) {
                synchronized(WordRoomDatabase::class) {
                    INSTANCE = buildRoomDB(context)
                }
            }
            return INSTANCE!!
        }

        private fun buildRoomDB(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                WordRoomDatabase::class.java,
                DATABASE_FILE_NAME
            ).build()
    }
}