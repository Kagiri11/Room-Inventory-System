package com.example.myshop.data

import androidx.room.*
import com.example.myshop.model.Item

@Dao
interface ShopDatabaseDao {

    @Delete
    suspend fun deleteItem(item: Item)

    @Query("SELECT * FROM item_table ORDER BY id  DESC")
    fun getItems():List<Item>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItem(item: Item)


}