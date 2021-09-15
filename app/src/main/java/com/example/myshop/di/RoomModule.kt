package com.example.myshop.di

import android.content.Context
import androidx.room.Room
import com.example.myshop.data.ShopDataBase
import com.example.myshop.data.ShopDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideShopDataBase(@ApplicationContext context: Context):ShopDataBase{
        return Room.databaseBuilder(context,ShopDataBase::class.java,ShopDataBase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideShopDBDao(dataBase: ShopDataBase):ShopDatabaseDao{
        return dataBase.shopDBDao
    }



}