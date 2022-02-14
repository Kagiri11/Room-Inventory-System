package com.kagiri.myshop.data.repositories

import com.kagiri.myshop.data.ShopDatabaseDao
import com.kagiri.myshop.domain.model.DailyProfits
import com.kagiri.myshop.domain.model.Item
import com.kagiri.myshop.domain.model.SoldEntry
import com.kagiri.myshop.domain.repository.ShopRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ShopRepositoryImpl
@Inject constructor(
   private val shopDao: ShopDatabaseDao
) : ShopRepository {

    override suspend fun addItem(item: Item)=shopDao.addItem(item)
    override suspend fun deleteItem(item: Item)=shopDao.deleteItem(item)
    override suspend fun getItems()=shopDao.getItems()

    override suspend fun searchItemsByName(nameOfItem : String) = shopDao.searchItemsByName(nameOfItem)

    override fun getSellEntries(): Flow<List<SoldEntry>> {
        return shopDao.getSellEntries()
    }

    override suspend fun addEntry(entry: SoldEntry) {
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