package com.avin.news.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.avin.news.BR
import com.avin.news.R
import com.avin.news.databinding.ItemNewsBinding
import com.avin.news.model.NewsData

class NewsListAdapter(var context: Context, var newsList: ArrayList<NewsData>) :
    RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

    lateinit var onItemClick: ((String) -> Unit?)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding: ItemNewsBinding =
            DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_news, parent, false)
        return ViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(newsList.get(position))
    }

    override fun getItemCount(): Int = newsList.size

    class ViewHolder(
        val binding: ItemNewsBinding, val onItemClick: (String) -> Unit?
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Any) {
            binding.setVariable(BR.news, data)
            binding.executePendingBindings()

            binding.clRoot.setOnClickListener {
                onItemClick.invoke(binding.news?.url.toString())
            }
        }
    }
}