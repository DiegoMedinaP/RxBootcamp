package com.example.rxbootcamp.data.model

import com.google.gson.annotations.SerializedName

data class BookEntity(
    val success: Boolean,
    @SerializedName("payload")
    val currencies: ArrayList<BookInfoEntity>
)

data class BookInfoEntity(
    @SerializedName("book")
    val name: String?
)