package com.example.rxbootcamp.data

import com.example.rxbootcamp.data.model.BookEntity
import com.example.rxbootcamp.data.model.TickerEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyService {

    @GET("available_books")
    suspend fun getAvailableBooks(): BookEntity

    @GET("ticker")
    suspend fun getCurrencyTicker(@Query("book") book: String): TickerEntity

}