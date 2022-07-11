package com.example.rxbootcamp.data

import com.example.rxbootcamp.ui.model.Book
import com.example.rxbootcamp.ui.model.Ticker
import io.reactivex.Single

interface CurrencyDataSource {
    fun getAvailableBooks(): Single<List<Book>>
    fun getCurrencyTicker(book: String): Single<Ticker>
}