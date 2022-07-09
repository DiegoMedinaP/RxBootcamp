package com.example.rxbootcamp.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.rxbootcamp.data.CurrencyDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject
import io.reactivex.schedulers.Schedulers

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataSource: CurrencyDataSource
): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    fun fetchCurrencies(){
        dataSource.getAvailableBooks()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.i("TAG","${it.size}")
            },{
                Log.i("TAG","${it.message}")
            }).addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}