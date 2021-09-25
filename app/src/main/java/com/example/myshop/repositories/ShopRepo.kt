package com.example.myshop.repositories

import com.example.myshop.model.Item
import com.example.myshop.model.SellEntry

interface ShopRepo {

    suspend fun addItem(item : Item)
    suspend fun deleteItem(item:Item)
    suspend fun getItems():List<Item>

    suspend fun getSellEntries():List<SellEntry>

}