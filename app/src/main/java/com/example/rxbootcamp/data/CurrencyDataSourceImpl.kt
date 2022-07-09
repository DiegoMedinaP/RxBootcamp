package com.example.rxbootcamp.data

import com.example.rxbootcamp.ui.model.Currency
import com.example.rxbootcamp.ui.model.Ticker
import io.reactivex.Single
import javax.inject.Inject

class CurrencyDataSourceImpl @Inject constructor(
    private val currencyService: CurrencyService
) : CurrencyDataSource {

    override fun getAvailableBooks(): Single<List<Currency>> =
        currencyService.getAvailableBooks().map {
            it.currencies.toCurrenciesViewData().filter { currency ->
                currency.book.contains("_mxn")
            }
        }

    override fun getCurrencyTicker(book: String): Single<Ticker> =
        currencyService.getCurrencyTicker(book).map { it.info.toTickerViewData() }
}