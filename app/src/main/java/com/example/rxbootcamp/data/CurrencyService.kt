package com.example.rxbootcamp.data

import com.example.rxbootcamp.data.model.BookEntity
import com.example.rxbootcamp.data.model.TickerEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyService {

    @GET("available_books")
    fun getAvailableBooks(): Single<BookEntity>

    @GET("ticker")
    fun getCurrencyTicker(@Query("book") book: String): Single<TickerEntity>

}