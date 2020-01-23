package com.example.translator.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.translator.R
import com.example.translator.adapter.RecyclerViewAdapter.ViewHolder

class RecyclerViewAdapter(private val list: Array<String>) : RecyclerView.Adapter<ViewHolder>() {

    companion object {
        lateinit var clickListener: ClickListener
    }
    fun setOnClickListener(clickListener: ClickListener) {
        RecyclerViewAdapter.clickListener = clickListener
    }
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.languageName.text = list[position]
    }
    override fun getItemCount(): Int = list.size
    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val item: View = inflater.inflate(R.layout.list, viewGroup, false)
        return ViewHolder(item)
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val languageName = itemView.findViewById(R.id.textViewInList) as TextView

        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            clickListener.onItemClick(list[adapterPosition])
        }
    }
    interface ClickListener {
        fun onItemClick(string: String)
    }
}