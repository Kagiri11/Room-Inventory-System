package com.example.myshop.data

import androidx.room.*
import com.example.myshop.model.Item
import com.example.myshop.model.Stock

@Dao
interface ShopDatabaseDao {

    @Query("SELECT * FROM stock_table ORDER BY stockId DESC")
    fun getStocks():List<Stock>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addStock(stock: Stock)

    @Delete
    suspend fun deleteStock(stock: Stock)

    @Query("SELECT * FROM item_table ORDER BY id  DESC")
    fun getItems():List<Item>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItem(item: Item)


}