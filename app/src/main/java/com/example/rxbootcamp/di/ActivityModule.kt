package com.example.rxbootcamp.di

import com.example.rxbootcamp.data.CurrencyDataSource
import com.example.rxbootcamp.data.CurrencyDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityModule {

    @Binds
    abstract fun bindsCurrencyDataSource(
        dataSource : CurrencyDataSourceImpl
    ) : CurrencyDataSource
}