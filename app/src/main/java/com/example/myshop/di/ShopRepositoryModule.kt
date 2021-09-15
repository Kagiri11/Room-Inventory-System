package com.example.myshop.di

import com.example.myshop.data.ShopDatabaseDao
import com.example.myshop.repository.ShopRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ShopRepositoryModule {

    fun provideShopRepository(dao: ShopDatabaseDao):ShopRepository{
        return ShopRepository(dao)
    }

}