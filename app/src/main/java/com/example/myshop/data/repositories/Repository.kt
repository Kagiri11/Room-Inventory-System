package com.example.myshop.data.repositories

import com.example.myshop.model.Item
import com.example.myshop.model.SellEntry

interface Repository {

    suspend fun addItem(item : Item)
    suspend fun deleteItem(item:Item)
    suspend fun getItems():List<Item>

    suspend fun getSellEntries():List<SellEntry>

    suspend fun addEntry(entry: SellEntry)

    suspend fun searchItemsByName(nameOfItem: String):List<Item>

}