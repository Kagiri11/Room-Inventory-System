package com.example.myshop.ui.sell

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myshop.data.repositories.Repository
import com.example.myshop.model.Item
import com.example.myshop.model.SellEntry
import com.example.myshop.utils.Cart
import kotlinx.coroutines.launch

class SellViewModel
@ViewModelInject constructor(private val shopRepository: Repository) : ViewModel() {

    private var dbItems = listOf<Item>()
    private val _itemsByName = MutableLiveData<List<Item>>()
    val itemsByName: LiveData<List<Item>> = _itemsByName

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
        Cart.addItemToCart(item)
        _sellCart.value = Cart.space.toList()
    }

    fun removeFromCart(item: Item) {
        Cart.removeItemFromCart(item)
        _sellCart.value = Cart.space.toList()
    }

    private fun deleteItem(item: Item) = viewModelScope.launch {
        shopRepository.deleteItem(item)
    }

    fun sellCart(timeOfSell:String) {
        addEntry(SellEntry(
            soldItems = Cart.space.map { it.name },
            timeSold = timeOfSell,
            totalProfit = Cart.space.sumOf { it.profit }))
        /**
         * Here I am finding any item in the database that matches the name of the current item being looped
         * in the cart and deleting that item from the database
         */
        Cart.apply {
            space.forEach { itemInCart ->
                val sameItemInDb = dbItems.first { it.name == itemInCart.name }
                deleteItem(sameItemInDb)
            }
            refreshCart()
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