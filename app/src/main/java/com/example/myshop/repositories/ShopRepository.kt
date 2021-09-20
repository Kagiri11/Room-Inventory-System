package com.example.myshop.repositories

import com.example.myshop.data.ShopDatabaseDao
import com.example.myshop.model.Item
import javax.inject.Inject

class ShopRepository 
@Inject constructor(
   private val shopDao: ShopDatabaseDao
) : ShopRepo {

    override suspend fun addItem(item: Item)=shopDao.addItem(item)

    override suspend fun deleteItem(item: Item)=shopDao.deleteItem(item)

    override suspend fun getItems()=shopDao.getItems()

}