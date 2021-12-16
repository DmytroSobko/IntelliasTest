package com.example.androidbootcampintellias.view.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidbootcampintellias.R
import com.example.androidbootcampintellias.data.WordItem
import com.example.androidbootcampintellias.view.activities.WordMeaningsActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.word_item.view.*

const val WORD_ITEM_INTENT_EXTRA_NAME = "wordItem"

class WordRecyclerViewAdapter ( private val context: Context) :
    RecyclerView.Adapter<WordRecyclerViewAdapter.ViewHolder>() {
    private var list: List<WordItem> = ArrayList()

    fun setWordList(list: List<WordItem>){
        this.list = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.wordNumber.text = (position + 1).toString()
        holder.word.text = list[position].word
        holder.rootView.setOnClickListener {
            val intent = Intent(context, WordMeaningsActivity::class.java)
            val gson = Gson()
            intent.putExtra(WORD_ITEM_INTENT_EXTRA_NAME, gson.toJson(list[position]))
            context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.word_item,
                parent,
                false
            )
        )
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val wordNumber = v.tv_word_number!!
        val word = v.tv_word!!
        val rootView = v.root_view!!
    }
}