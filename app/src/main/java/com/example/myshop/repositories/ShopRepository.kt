package com.example.myshop.repositories

import com.example.myshop.data.ShopDatabaseDao
import com.example.myshop.model.Item
import com.example.myshop.model.SellEntry
import javax.inject.Inject

class ShopRepository 
@Inject constructor(
   private val shopDao: ShopDatabaseDao
) : ShopRepo {

    override suspend fun addItem(item: Item)=shopDao.addItem(item)
    override suspend fun deleteItem(item: Item)=shopDao.deleteItem(item)
    override suspend fun getItems()=shopDao.getItems()

    suspend fun searchItemsByName(itemName : String) = shopDao.searchItemsByName(itemName)

    override suspend fun getSellEntries(): List<SellEntry> {
        return shopDao.getSellEntries()
    }

    override suspend fun addEntry(entry: SellEntry) {
        return shopDao.addSellEntry(entry)
    }

}