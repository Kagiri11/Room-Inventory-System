package com.example.myshop.ui.sell

import android.annotation.SuppressLint
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myshop.data.repositories.Repository
import com.example.myshop.model.DailyProfits
import com.example.myshop.model.Item
import com.example.myshop.model.SellEntry
import com.example.myshop.utils.Cart
import com.example.myshop.utils.TimeOfSell
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class SellViewModel
@ViewModelInject constructor(private val shopRepository: Repository) : ViewModel() {

    @SuppressLint("SimpleDateFormat")
    val format = SimpleDateFormat("dd/M/yyyy")
    private val dateToday: String = format.format(Calendar.getInstance().time)

    private var dbItems = listOf<Item>()

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

    fun sellCart() {
        addEntry(
            SellEntry(
                soldItems = Cart.space.map { it.name },
                timeSold = TimeOfSell.stamp(),
                totalProfit = Cart.space.sumOf { it.profit })
        )
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

    suspend fun getItemsByName(itemName: String): Flow<List<Item>> {
        return shopRepository.searchItemsByName(itemName)
    }

    private fun addEntry(entry: SellEntry) {
        viewModelScope.launch {
            shopRepository.addEntry(entry)
        }
    }

    private fun getSellEntries() = shopRepository.getSellEntries()

    fun addDailyProfit() {
        viewModelScope.launch {
            getSellEntries().collect { listOfSoldThings ->
                val profit = listOfSoldThings.filter { it.timeSold.contains(dateToday) }
                    .sumOf { it.totalProfit }
                val dailyProfit = DailyProfits(dateToday, profit)

                if (profit == 0.0) {
                    shopRepository.addDailyProfit(dailyProfit)
                } else {
                    shopRepository.deleteDailyProfit(dateToday)
                    shopRepository.addDailyProfit(dailyProfit)
                }
            }
        }
    }

    suspend fun getDailyProfits() = shopRepository.getDailyProfits()

}