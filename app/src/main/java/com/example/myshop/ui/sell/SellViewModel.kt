package com.example.myshop.ui.sell

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myshop.data.repositories.ShopRepo
import com.example.myshop.model.Item
import com.example.myshop.model.SellEntry
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class SellViewModel
@ViewModelInject constructor(private val shopRepository: ShopRepo) : ViewModel() {
    private var dbItems = listOf<Item>()
    private val _itemsByName = MutableLiveData<List<Item>>()
    val itemsByName: LiveData<List<Item>> = _itemsByName
    private val cartList = mutableListOf<Item>()

    init {
        prepareDbItems()
    }

    private fun prepareDbItems() {
        viewModelScope.launch {
            dbItems = shopRepository.getItems()
        }
    }

    private val _sellCart = MutableLiveData<List<Item>>()
    val sellCart: LiveData<List<Item>> = _sellCart

    fun addToCart(item: Item) {
        cartList.add(item)
        _sellCart.value = cartList.toList()
    }

    fun removeFromCart(item: Item) {
        cartList.remove(item)
        _sellCart.value = cartList.toList()
    }

    private fun deleteItem(item: Item) = viewModelScope.launch {
        shopRepository.deleteItem(item)
    }

    fun sellCart(timeOfSell:String) {
        val entry =
            SellEntry(
                soldItems = cartList.map { it.name },
                timeSold = timeOfSell,
                totalProfit = cartList.sumOf { it.profit })

        addEntry(entry)
        /**
         * Here I am finding any item in the database that matches the name of the current item being looped
         * in the cart and deleting that item from the database
         */
        cartList.apply {
            forEach { itemInCart ->
                val sameItemInDb = dbItems.first { it.name == itemInCart.name }
                deleteItem(sameItemInDb)
            }
            clear()
        }
    }

    fun searchItemByName(itemName: String) = viewModelScope.launch {
        val items = shopRepository.searchItemsByName(itemName)
        _itemsByName.value = items.toList().take(1)
    }

    private fun addEntry(entry: SellEntry) {
        viewModelScope.launch {
            shopRepository.addEntry(entry)
        }
    }
}