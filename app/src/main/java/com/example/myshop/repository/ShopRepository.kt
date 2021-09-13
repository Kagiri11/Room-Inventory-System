package com.example.myshop.repository

import com.example.myshop.data.ShopDatabaseDao
import com.example.myshop.model.Item
import javax.inject.Inject

class ShopRepository 
@Inject constructor(
   private val shopDao: ShopDatabaseDao
) {

    suspend fun addItem(item: Item)=shopDao.addItem(item)

    suspend fun deleteItem(item: Item)=shopDao.deleteItem(item)

    fun getItems()=shopDao.getItems()

    fun getTotalPriceInDB():Double{
        val items= getItems()
        return items.sumOf { it.buyingPrice }
    }

}