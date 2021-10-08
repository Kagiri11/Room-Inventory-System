package com.example.myshop.data.repositories

import com.example.myshop.data.ShopDatabaseDao
import com.example.myshop.model.DailyProfits
import com.example.myshop.model.Item
import com.example.myshop.model.SellEntry
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ShopRepository 
@Inject constructor(
   private val shopDao: ShopDatabaseDao
) : Repository {

    override suspend fun addItem(item: Item)=shopDao.addItem(item)
    override suspend fun deleteItem(item: Item)=shopDao.deleteItem(item)
    override suspend fun getItems()=shopDao.getItems()

    override suspend fun searchItemsByName(nameOfItem : String) = shopDao.searchItemsByName(nameOfItem)

    override fun getSellEntries(): Flow<List<SellEntry>> {
        return shopDao.getSellEntries()
    }

    override suspend fun addEntry(entry: SellEntry) {
        return shopDao.addSellEntry(entry)
    }

    override suspend fun addDailyProfit(dailyProfit: DailyProfits) {
        return shopDao.addDailyProfit(dailyProfit)
    }

    override suspend fun deleteDailyProfit(date:String) {
        return shopDao.deleteDailyProfit(date)
    }

    override fun getDailyProfits():Flow<List<DailyProfits>> {
        return shopDao.getDailyProfits()
    }
}