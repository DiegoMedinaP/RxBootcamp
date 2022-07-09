package com.example.rxbootcamp.data

import com.example.rxbootcamp.data.model.CurrencyInfoEntity
import com.example.rxbootcamp.data.model.TickerInfoEntity
import com.example.rxbootcamp.ui.model.Currency
import com.example.rxbootcamp.ui.model.Ticker


fun ArrayList<CurrencyInfoEntity>.toCurrenciesViewData(): List<Currency> = this.map {
    it.toCurrencyViewData()
}

fun CurrencyInfoEntity.toCurrencyViewData() = Currency(
    book.orEmpty()
)

fun TickerInfoEntity.toTickerViewData() = Ticker(
    high.orEmpty(),
    last.orEmpty(),
    created_at.orEmpty(),
    book.orEmpty(),
    low.orEmpty(),
)