package com.example.androidbootcampintellias.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidbootcampintellias.R
import com.example.androidbootcampintellias.view.adapters.WordRecyclerViewAdapter
import com.example.androidbootcampintellias.viewModel.SearchWordViewModel
import kotlinx.android.synthetic.main.activity_search_word.*

class SearchWordActivity : AppCompatActivity() {
    private lateinit var viewModel: SearchWordViewModel
    private lateinit var adapter: WordRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_word)

        viewModel = ViewModelProvider(this)[SearchWordViewModel::class.java]

        initUi()
    }

    private fun initUi(){
        iv_search.setOnClickListener {
            if(et_search.text!!.isNotEmpty())
                viewModel.searchWord(et_search.text.toString())
        }

        viewModel.wordDefinitionsList.observe(this, {
            adapter.setWordList(it)
        })

        initRecyclerView()
    }

    private fun initRecyclerView(){
        adapter = WordRecyclerViewAdapter(this)
        rv_search.adapter = adapter
        rv_search.layoutManager = LinearLayoutManager(this)
    }
}