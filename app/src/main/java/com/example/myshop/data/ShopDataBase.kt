package com.example.myshop.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myshop.model.Item

@Database(entities = [Item::class],version = 1,exportSchema = false)
abstract class ShopDataBase : RoomDatabase(){

    abstract val shopDBDao : ShopDatabaseDao

    companion object{
        private var INSTANCE : ShopDataBase?=null
        const val DATABASE_NAME = "shop_db"
    }
}