package com.example.rxbootcamp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.rxbootcamp.databinding.ActivityMainBinding
import com.example.rxbootcamp.ui.model.Currency
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private val viewModel : MainViewModel by viewModels()
    private val adapter : CurrencyAdapter by lazy {
        CurrencyAdapter { currency , position ->
            currencyClickListener(currency, position)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUp()
        setObservers()
    }

    private fun setUp(){
        viewModel.fetchCurrencies()
        binding.rvBooks.adapter = adapter
    }


    private fun currencyClickListener(currency : Currency, position:Int){
        viewModel.fetchCurrencyPrice(currency.bookName,position)
        Toast.makeText(this, currency.bookName,Toast.LENGTH_SHORT).show()
    }

    private fun setObservers() {
        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.currencies.collect { currencies ->
                    adapter.submitList(currencies)
                }
            }
        }
    }


}