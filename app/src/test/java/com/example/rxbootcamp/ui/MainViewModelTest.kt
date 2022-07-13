package com.example.rxbootcamp.ui

import com.example.rxbootcamp.RxSchedulerRule
import com.example.rxbootcamp.data.CurrencyDataSource
import com.example.rxbootcamp.ui.model.Book
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class MainViewModelTest {

    @get:Rule
    val rxSchedulerRule =  RxSchedulerRule()

    private lateinit var classUnderTest: MainViewModel
    private val dataSource: CurrencyDataSource = mock()

    @Before
    fun setUp(){
        classUnderTest = MainViewModel(dataSource)
    }

    @Test
    fun `init fetch currencies`() {

        whenever(dataSource.getAvailableBooks()).thenReturn(mockResponse)

        classUnderTest.fetchCurrencies()
        Assert.assertEquals(
            classUnderTest.currencies.value.size, 4
        )

    }

    private val mockResponse = Single.just(
        listOf(
            Book(name = "btc_mxn"),
            Book(name = "eth_mxn"),
            Book(name = "xrp_mxn"),
            Book(name = "Itc_mxn")
        )
    )


}