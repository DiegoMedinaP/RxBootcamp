package com.example.rxbootcamp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rxbootcamp.databinding.ItemCurrencyBinding
import com.example.rxbootcamp.ui.model.Currency

class BookAdapter : ListAdapter<Currency, BookAdapter.ViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCurrencyBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position).book)
    }

    companion object {
        object COMPARATOR : DiffUtil.ItemCallback<Currency>(){
            override fun areItemsTheSame(oldItem: Currency, newItem: Currency): Boolean =
                oldItem.book == newItem.book


            override fun areContentsTheSame(oldItem: Currency, newItem: Currency): Boolean =
                oldItem == newItem
        }
    }

    inner class ViewHolder(private val binding: ItemCurrencyBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(book:String){
            binding.bookName.text = book
        }
    }
}