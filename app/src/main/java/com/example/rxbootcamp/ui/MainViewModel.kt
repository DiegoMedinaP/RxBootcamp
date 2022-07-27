package com.example.rxbootcamp.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rxbootcamp.data.CurrencyDataSource
import com.example.rxbootcamp.data.CurrencyDataSourceImpl
import com.example.rxbootcamp.ui.model.Currency
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.RuntimeException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val currencyDataSource: CurrencyDataSource
) : ViewModel() {

    private val _currencies = MutableStateFlow<List<Currency>>(emptyList())
    val currencies = _currencies.asStateFlow()

    val flow1 = (1..10).asFlow().onEach {
        if(it == 8) throw RuntimeException()
        delay(100)
    }
    val flow2 = (20..30).asFlow().onEach { delay(1000) }

    fun getCurrencies(){
        viewModelScope.launch {
            val currencies = currencyDataSource.getAvailableBooks().map {
                val ticker = currencyDataSource.getCurrencyTicker(it.name)
                Currency(it.name,ticker.last)
            }
            _currencies.value = currencies
        }
    }

    fun doSomething(){

        flow1
            .catch {
                Log.i("MERGED","Ocurrio un Error")
            }
            .onEach {
                Log.i("MERGED","$it")
            }.launchIn(viewModelScope)

        flow1.zip(flow2){ num1 , num2 ->
            Log.i("MERGED","$num1  || $num2")
        }.launchIn(viewModelScope)

        //merge(flow1,flow2).onEach {
        //    Log.i("MERGED",it.toString())
        //}.launchIn(viewModelScope)



    }

}