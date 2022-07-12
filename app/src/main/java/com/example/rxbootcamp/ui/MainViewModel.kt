package com.example.rxbootcamp.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.rxbootcamp.data.CurrencyDataSource
import com.example.rxbootcamp.ui.model.Currency
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataSource: CurrencyDataSource
) : ViewModel() {

    private val _currencies = MutableStateFlow<List<Currency>>(emptyList())
    val currencies = _currencies.asStateFlow()

    private val compositeDisposable = CompositeDisposable()

    fun fetchCurrencies() {
        dataSource.getAvailableBooks()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    val currencies = it.map { book -> Currency(book.name) }
                    _currencies.value = currencies
                },
                onError = {
                    Log.i("TAG", "${it.message}")
                }
            ).addTo(compositeDisposable)
    }

    fun fetchCurrencyPrice(book: String, currencyIndex: Int) {
        compositeDisposable.add(
            dataSource.getCurrencyTicker(book)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ ticker ->
                    val currencies = _currencies.value.toMutableList()
                    currencies[currencyIndex] = currencies[currencyIndex].copy(
                        price = ticker.last
                    )
                    _currencies.value = currencies
                }, {
                    Log.i("TAG", "${it.message}")
                }
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}