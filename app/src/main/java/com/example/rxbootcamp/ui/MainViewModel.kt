package com.example.rxbootcamp.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.rxbootcamp.data.CurrencyDataSource
import com.example.rxbootcamp.ui.model.Currency
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataSource: CurrencyDataSource
): ViewModel() {

    private val _currencies = MutableStateFlow<List<Currency>>(emptyList())
    val currencies : StateFlow<List<Currency>> get() = _currencies

    private val compositeDisposable = CompositeDisposable()

    fun fetchCurrencies(){
        dataSource.getAvailableBooks()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _currencies.value = it
            },{
                Log.i("TAG","${it.message}")
            }).addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}