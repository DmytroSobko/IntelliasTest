package com.example.androidbootcampintellias.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidbootcampintellias.R
import com.example.androidbootcampintellias.data.Definition
import kotlinx.android.synthetic.main.definition_item.view.*

class DefinitionRecyclerViewAdapter  (private val context: Context) :
    RecyclerView.Adapter<DefinitionRecyclerViewAdapter.ViewHolder>() {
    private var list: List<Definition> = ArrayList()

    fun setDefinitionsList(list: List<Definition>){
        this.list = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.definitionNumber.text = (position + 1).toString()
        holder.definition.text = list[position].definition
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.definition_item,
                parent,
                false
            )
        )
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val definitionNumber = v.tv_definition_number!!
        val definition = v.tv_definition!!
    }
}