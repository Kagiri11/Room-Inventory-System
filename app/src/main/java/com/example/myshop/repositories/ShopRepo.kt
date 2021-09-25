package com.example.myshop.repositories

import com.example.myshop.model.Item
interface ShopRepo {

    suspend fun addItem(item : Item)
    suspend fun deleteItem(item:Item)
    suspend fun getItems():List<Item>

}