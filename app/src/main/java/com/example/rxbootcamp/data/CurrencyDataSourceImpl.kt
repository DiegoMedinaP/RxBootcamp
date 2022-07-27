package com.example.rxbootcamp.data

import com.example.rxbootcamp.ui.model.Book
import com.example.rxbootcamp.ui.model.Currency
import com.example.rxbootcamp.ui.model.Ticker
import io.reactivex.Single
import javax.inject.Inject

class CurrencyDataSourceImpl @Inject constructor(
    private val currencyService: CurrencyService
) : CurrencyDataSource {

    override suspend fun getAvailableBooks(): List<Book> =
        currencyService.getAvailableBooks().currencies.toCurrenciesViewData().filter { book ->
                book.name.contains("_mxn")
            }


    override suspend fun getCurrencyTicker(book: String): Ticker =
        currencyService.getCurrencyTicker(book).info.toTickerViewData()
}