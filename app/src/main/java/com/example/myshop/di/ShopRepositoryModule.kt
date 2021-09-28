package com.example.myshop.di

import com.example.myshop.data.ShopDatabaseDao
import com.example.myshop.data.repositories.ShopRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ShopRepositoryModule {

    @Provides
    @Singleton
    fun provideShopRepository(dao: ShopDatabaseDao): ShopRepository {
        return ShopRepository(dao)
    }

}