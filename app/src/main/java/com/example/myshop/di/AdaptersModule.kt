package com.example.myshop.di

import com.example.myshop.ui.adapters.CartItemAdapter
import com.example.myshop.ui.adapters.CartListStringAdapter
import com.example.myshop.ui.adapters.SellHistoryAdapter
import com.example.myshop.ui.adapters.SellItemAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AdaptersModule {

    @Provides
    @Singleton
    fun provideSellHistoryAdapter() = SellHistoryAdapter()

    @Provides
    @Singleton
    fun provideCartItemAdapter() = CartItemAdapter()

    @Provides
    @Singleton
    fun sellItemAdapter() = SellItemAdapter()


}