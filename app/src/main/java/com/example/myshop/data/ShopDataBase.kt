package com.example.myshop.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myshop.model.Item
import com.example.myshop.model.SellEntry
import com.example.myshop.utils.Konvatas

@Database(entities = [Item::class,SellEntry::class],version = 1,exportSchema = false)
@TypeConverters(Konvatas::class)
abstract class ShopDataBase : RoomDatabase(){

    abstract val shopDBDao : ShopDatabaseDao

    companion object{
        private var INSTANCE : ShopDataBase?=null
        const val DATABASE_NAME = "shop_db"
    }
}