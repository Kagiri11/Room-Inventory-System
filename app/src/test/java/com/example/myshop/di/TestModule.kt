package com.example.myshop.di

import com.example.myshop.data.repositories.FakeRepositoryImpl
import com.example.myshop.domain.repository.ShopRepository
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