package com.kagiri.myshop.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kagiri.myshop.domain.model.DailyProfits
import com.kagiri.myshop.domain.model.Item
import com.kagiri.myshop.domain.model.SoldEntry
import com.kagiri.myshop.utils.Konvatas

@Database(entities = [Item::class,SoldEntry::class,DailyProfits::class],version = 3,exportSchema = false)
@TypeConverters(Konvatas::class)
abstract class ShopDataBase : RoomDatabase(){

    abstract val shopDBDao : ShopDatabaseDao

    companion object{
        const val DATABASE_NAME = "shop_db"
    }
}