package com.example.testmvvmapp.adapter.repositoryRecyclerViewAdapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.testmvvmapp.data.repository.Repository
import com.example.testmvvmapp.databinding.RvItemRepositoryBinding

class RepositoryRecyclerViewAdapter(private var items: ArrayList<Repository>,
                                    private var listener: OnItemClickListener
):
    RecyclerView.Adapter<RepositoryRecyclerViewAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RvItemRepositoryBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener)

    override fun getItemCount(): Int = items.size

    fun replaceData(array: ArrayList<Repository>)
    {
        items.clear()
        items.addAll(array)
        this.notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
    class ViewHolder(private var binding: RvItemRepositoryBinding): RecyclerView.ViewHolder(binding.root)
    {
        fun bind(repo: Repository, listener: OnItemClickListener?)
        {
            binding.repository = repo
            if(listener != null)
            {
                binding.root.setOnClickListener { listener.onItemClick(layoutPosition)}
            }
            binding.executePendingBindings()
        }
    }
}