package com.example.rxbootcamp.data

import com.example.rxbootcamp.ui.model.Book
import com.example.rxbootcamp.ui.model.Ticker
import io.reactivex.Single

interface CurrencyDataSource {
    suspend fun getAvailableBooks(): List<Book>
    suspend fun getCurrencyTicker(book: String): Ticker
}