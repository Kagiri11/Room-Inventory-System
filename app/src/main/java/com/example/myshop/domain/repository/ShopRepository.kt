package com.example.myshop.domain.repository

import com.example.myshop.domain.model.DailyProfits
import com.example.myshop.domain.model.Item
import com.example.myshop.domain.model.SoldEntry
import kotlinx.coroutines.flow.Flow

interface ShopRepository {

    suspend fun addItem(item : Item)
    suspend fun deleteItem(item:Item)
    suspend fun getItems():List<Item>

    fun getSellEntries():Flow<List<SoldEntry>>

    suspend fun addEntry(entry: SoldEntry)

    suspend fun searchItemsByName(nameOfItem: String):Flow<List<Item>>

    suspend fun addDailyProfit(dailyProfit: DailyProfits)

    suspend fun deleteDailyProfit(date: String)

    fun getDailyProfits():Flow<List<DailyProfits>>

}