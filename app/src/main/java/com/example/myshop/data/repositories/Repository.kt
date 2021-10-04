package com.example.myshop.data.repositories

import com.example.myshop.model.Item
import com.example.myshop.model.SellEntry
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun addItem(item : Item)
    suspend fun deleteItem(item:Item)
    suspend fun getItems():List<Item>

    fun getSellEntries():Flow<List<SellEntry>>

    suspend fun addEntry(entry: SellEntry)

    suspend fun searchItemsByName(nameOfItem: String):Flow<List<Item>>

}