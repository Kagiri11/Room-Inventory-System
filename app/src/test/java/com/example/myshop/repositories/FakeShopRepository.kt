package com.example.myshop.repositories

import androidx.lifecycle.MutableLiveData
import com.example.myshop.data.repositories.ShopRepo
import com.example.myshop.model.Item
import com.example.myshop.model.SellEntry

class FakeShopRepository : ShopRepo {

    val stockList = mutableListOf<Item>()

    val observableListOfStock = MutableLiveData<List<Item>>(stockList)

    override suspend fun addItem(item: Item) {
        stockList.add(item)
    }

    override suspend fun deleteItem(item: Item) {
        stockList.remove(item)
    }

    override suspend fun getItems(): List<Item> {
        return stockList
    }

    override suspend fun getSellEntries(): List<SellEntry> {
        return emptyList()
    }
}