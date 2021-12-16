package com.example.androidbootcampintellias.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidbootcampintellias.R
import com.example.androidbootcampintellias.data.Definition
import com.example.androidbootcampintellias.view.adapters.DEFINITIONS_INTENT_EXTRA_NAME
import com.example.androidbootcampintellias.view.adapters.DefinitionRecyclerViewAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_word_definitions.*

class WordDefinitionsActivity : AppCompatActivity() {
    private lateinit var adapter: DefinitionRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_definitions)

        val definitions = getIncomingIntent()

        if(definitions != null){
            initUi(definitions)
        }
    }

    private fun getIncomingIntent():List<Definition>?{
        if(intent.hasExtra(DEFINITIONS_INTENT_EXTRA_NAME)){
            val extra = intent.getStringExtra(DEFINITIONS_INTENT_EXTRA_NAME)
            val gson = Gson()
            val itemType = object : TypeToken<List<Definition>>() {}.type
            return gson.fromJson(extra, itemType)
        }

        return null
    }

    private fun initUi(definitions : List<Definition>){
        initRecyclerView()

        adapter.setDefinitionsList(definitions)
    }

    private fun initRecyclerView(){
        adapter = DefinitionRecyclerViewAdapter(this)
        rv_definition.adapter = adapter
        rv_definition.layoutManager = LinearLayoutManager(this)
    }
}