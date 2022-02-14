package com.kagiri.myshop.di

import com.kagiri.myshop.data.repositories.FakeRepositoryImpl
import com.kagiri.myshop.domain.repository.ShopRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object TestModule {

    @Provides
    @Singleton
    fun providesTestRepository(): ShopRepository{
        return FakeRepositoryImpl()
    }
}