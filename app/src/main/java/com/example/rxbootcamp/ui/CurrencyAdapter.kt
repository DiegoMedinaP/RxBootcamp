package com.example.rxbootcamp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rxbootcamp.databinding.ItemCurrencyBinding
import com.example.rxbootcamp.ui.model.Currency

class CurrencyAdapter(private val currencyClickListener: (currency :Currency, position : Int) -> Unit) :
    ListAdapter<Currency, CurrencyAdapter.ViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCurrencyBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position),position)
    }

    companion object {
        object COMPARATOR : DiffUtil.ItemCallback<Currency>() {
            override fun areItemsTheSame(oldItem: Currency, newItem: Currency): Boolean =
                oldItem.bookName == newItem.bookName


            override fun areContentsTheSame(oldItem: Currency, newItem: Currency): Boolean =
                oldItem == newItem
        }
    }

    inner class ViewHolder(private val binding: ItemCurrencyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(currency: Currency, position: Int) {
            binding.bookName.text = currency.bookName
            binding.bookPrice.text = currency.price
            binding.root.setOnClickListener { currencyClickListener(currency, position) }
        }
    }
}