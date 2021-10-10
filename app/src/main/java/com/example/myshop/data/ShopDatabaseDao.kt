package com.example.myshop.data

import androidx.room.*
import com.example.myshop.domain.model.DailyProfits
import com.example.myshop.domain.model.Item
import com.example.myshop.domain.model.SoldEntry
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
    fun searchItemsByName(itemName : String):Flow<List<Item>>

    @Query("SELECT * FROM sells_table ORDER BY id DESC")
    fun getSellEntries():Flow<List<SoldEntry>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSellEntry(entry:SoldEntry)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDailyProfit(dailyProfit: DailyProfits)

    @Query("DELETE FROM daily_profits WHERE date = :date")
    suspend fun deleteDailyProfit(date: String)

    @Query("SELECT * FROM daily_profits")
    fun getDailyProfits():Flow<List<DailyProfits>>

}