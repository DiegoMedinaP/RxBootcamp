package com.example.rxbootcamp.data

import com.example.rxbootcamp.data.model.BookEntity
import com.example.rxbootcamp.data.model.BookInfoEntity
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class CurrencyDataSourceImplTest {

    private lateinit var classUnderTest: CurrencyDataSource

    private val currencyService: CurrencyService = mock()

    @Before
    fun setUp() {
        classUnderTest = CurrencyDataSourceImpl(currencyService)
    }

    @Test
    fun when_fetch_currency_size_is_equal_as_mock() {
        whenever(currencyService.getAvailableBooks()).thenReturn(mockResponse)

        val result = classUnderTest.getAvailableBooks().test()
        Assert.assertEquals(result.values().firstOrNull()?.size, 4)
    }

    @Test
    fun when_fetch_currency_first_element_is_btc() {
        whenever(currencyService.getAvailableBooks()).thenReturn(mockResponse)

        val result = classUnderTest.getAvailableBooks().test()
        Assert.assertEquals(result.values().firstOrNull()?.firstOrNull()?.name,"btc_mxn")
    }

    private val mockResponse = Single.just(BookEntity(
        currencies = arrayListOf(
            BookInfoEntity(
                name = "btc_mxn"
            ),
            BookInfoEntity(
                name = "eth_mxn"
            ),
            BookInfoEntity(
                name = "xrp_mxn"
            ),
            BookInfoEntity(
                name = "Itc_mxn"
            ),
            BookInfoEntity(
                name = "btc_eth"
            )
        ),
        success = true
    ))


}