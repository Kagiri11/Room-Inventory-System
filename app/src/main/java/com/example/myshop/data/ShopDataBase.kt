package com.example.myshop.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myshop.model.Item
import com.example.myshop.model.Stock

@Database(entities = [Item::class,Stock::class],version = 1)
abstract class ShopDataBase : RoomDatabase(){

    abstract val shopDBDao : ShopDatabaseDao

    companion object{
        private var INSTANCE : ShopDataBase?=null
        val DATABASE_NAME = "shop_db"
    }

}