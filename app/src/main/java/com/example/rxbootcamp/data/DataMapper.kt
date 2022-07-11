package com.example.rxbootcamp.data

import com.example.rxbootcamp.data.model.BookInfoEntity
import com.example.rxbootcamp.data.model.TickerInfoEntity
import com.example.rxbootcamp.ui.model.Book
import com.example.rxbootcamp.ui.model.Ticker


fun ArrayList<BookInfoEntity>.toCurrenciesViewData(): List<Book> = this.map {
    it.toBookViewData()
}

fun BookInfoEntity.toBookViewData() = Book(
    name.orEmpty()
)

fun TickerInfoEntity.toTickerViewData() = Ticker(
    high.orEmpty(),
    last.orEmpty(),
    created_at.orEmpty(),
    book.orEmpty(),
    low.orEmpty(),
)