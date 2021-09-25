package com.example.myshop.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myshop.model.Item
import com.example.myshop.model.SellEntry
import kotlinx.coroutines.flow.Flow

@Dao
interface ShopDatabaseDao {

    @Delete
    suspend fun deleteItem(item: Item)

    @Query("SELECT * FROM item_table ORDER BY id  DESC")
    suspend fun getItems(): List<Item>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItem(item: Item)

    @Query("SELECT * FROM item_table WHERE name LIKE :itemName")
    suspend fun searchItemsByName(itemName : String):List<Item>

    suspend fun getSellEntries():List<SellEntry>
}