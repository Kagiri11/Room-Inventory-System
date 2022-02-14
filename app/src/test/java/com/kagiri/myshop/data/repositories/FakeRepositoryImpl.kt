package com.kagiri.myshop.data.repositories

import com.kagiri.myshop.domain.model.DailyProfits
import com.kagiri.myshop.domain.model.Item
import com.kagiri.myshop.domain.model.SoldEntry
import com.kagiri.myshop.domain.repository.ShopRepository
import com.kagiri.myshop.utils.Items
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class FakeRepositoryImpl: ShopRepository {


    val stockItems = mutableListOf(Items.itemOne)

    val sellEntries = mutableListOf<SoldEntry>()

    val dailyProfits = mutableListOf<DailyProfits>()

    override suspend fun addItem(item: Item) {
        stockItems.add(item)
    }

    override suspend fun deleteItem(item: Item) {
        stockItems.remove(item)
    }

    override suspend fun getItems(): List<Item> {
        return stockItems
    }

    override fun getSellEntries(): Flow<List<SoldEntry>> {
       return flowOf(sellEntries)
    }

    override suspend fun addEntry(entry: SoldEntry) {
        sellEntries.add(entry)
    }

    override suspend fun searchItemsByName(nameOfItem: String): Flow<List<Item>> {
        return flowOf(stockItems.filter { it.name == nameOfItem })
    }

    override suspend fun addDailyProfit(dailyProfit: DailyProfits) {
        dailyProfits.add(dailyProfit)
    }

    override suspend fun deleteDailyProfit(date: String) {
        dailyProfits.removeAll { it.date == date  }
    }

    override fun getDailyProfits(): Flow<List<DailyProfits>> {
        return flow {
            emit(dailyProfits)
        }
    }
}