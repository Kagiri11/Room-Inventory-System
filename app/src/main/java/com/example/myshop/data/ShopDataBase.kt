package com.example.myshop.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myshop.domain.model.DailyProfits
import com.example.myshop.domain.model.Item
import com.example.myshop.domain.model.SoldEntry
import com.example.myshop.utils.Konvatas

@Database(entities = [Item::class,SoldEntry::class,DailyProfits::class],version = 3,exportSchema = false)
@TypeConverters(Konvatas::class)
abstract class ShopDataBase : RoomDatabase(){

    abstract val shopDBDao : ShopDatabaseDao

    companion object{
        const val DATABASE_NAME = "shop_db"
    }
}