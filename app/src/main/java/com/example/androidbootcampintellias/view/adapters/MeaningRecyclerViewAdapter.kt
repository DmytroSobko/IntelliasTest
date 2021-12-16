package com.example.androidbootcampintellias.view.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidbootcampintellias.R
import com.example.androidbootcampintellias.data.Meaning
import com.example.androidbootcampintellias.view.activities.WordDefinitionsActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.meaning_item.view.*
import kotlinx.android.synthetic.main.word_item.view.root_view

const val DEFINITIONS_INTENT_EXTRA_NAME = "definitions"

class MeaningRecyclerViewAdapter (private val context: Context) :
    RecyclerView.Adapter<MeaningRecyclerViewAdapter.ViewHolder>() {
    private var list: List<Meaning> = ArrayList()

    fun setMeaningsList(list: List<Meaning>){
        this.list = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.wordNumber.text = (position + 1).toString()
        holder.partOfSpeech.text = list[position].partOfSpeech
        holder.rootView.setOnClickListener {
            val intent = Intent(context, WordDefinitionsActivity::class.java)
            val gson = Gson()
            val definitions = gson.toJson(list[position].definitions)

            intent.putExtra(DEFINITIONS_INTENT_EXTRA_NAME, definitions)
            context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.meaning_item,
                parent,
                false
            )
        )
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val wordNumber = v.tv_meaning_number!!
        val partOfSpeech = v.tv_part_of_speech!!
        val rootView = v.root_view!!
    }
}