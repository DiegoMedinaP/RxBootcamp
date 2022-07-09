package com.example.rxbootcamp.data

import com.example.rxbootcamp.ui.model.Currency
import com.example.rxbootcamp.ui.model.Ticker
import io.reactivex.Single

interface CurrencyDataSource {
    fun getAvailableBooks(): Single<List<Currency>>
    fun getCurrencyTicker(book: String): Single<Ticker>
}