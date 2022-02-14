package com.kagiri.myshop.di

import com.kagiri.myshop.data.ShopDatabaseDao
import com.kagiri.myshop.data.repositories.ShopRepositoryImpl
import com.kagiri.myshop.domain.repository.ShopRepository
import com.kagiri.myshop.presentation.ui.adapters.CartItemAdapter
import com.kagiri.myshop.presentation.ui.adapters.SellHistoryAdapter
import com.kagiri.myshop.presentation.ui.adapters.SellItemAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    //Adapters provided here
    @Provides
    @Singleton
    fun provideSellHistoryAdapter() = SellHistoryAdapter()

    @Provides
    @Singleton
    fun provideCartItemAdapter() = CartItemAdapter()

    @Provides
    @Singleton
    fun sellItemAdapter() = SellItemAdapter()

    //Repository details
    @Provides
    @Singleton
    fun provideRepository(dao: ShopDatabaseDao): ShopRepository {
        return ShopRepositoryImpl(dao)
    }

}