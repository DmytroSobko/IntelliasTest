package com.example.androidbootcampintellias.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidbootcampintellias.R
import com.example.androidbootcampintellias.data.WordItem
import com.example.androidbootcampintellias.view.adapters.MeaningRecyclerViewAdapter
import com.example.androidbootcampintellias.view.adapters.WORD_ITEM_INTENT_EXTRA_NAME
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_word_meanings.*
import kotlinx.android.synthetic.main.word_item.tv_word

class WordMeaningsActivity: AppCompatActivity() {
    private lateinit var adapter: MeaningRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_meanings)

        val wordItem = getIncomingIntent()

        if(wordItem != null){
            initUi(wordItem)
        }
    }

    private fun getIncomingIntent(): WordItem?{
        if(intent.hasExtra(WORD_ITEM_INTENT_EXTRA_NAME)) {
            val extra = intent.getStringExtra(WORD_ITEM_INTENT_EXTRA_NAME)
            val gson = Gson()

            return gson.fromJson(extra, WordItem::class.java)
        }

        return null
    }

    private fun initUi(wordItem : WordItem){
        tv_word.text = wordItem.word
        tv_phonetic.text = wordItem.phonetic

        initRecyclerView()

        adapter.setMeaningsList(wordItem.meanings)
    }

    private fun initRecyclerView(){
        adapter = MeaningRecyclerViewAdapter(this)
        rv_meaning.adapter = adapter
        rv_meaning.layoutManager = LinearLayoutManager(this)
    }
}