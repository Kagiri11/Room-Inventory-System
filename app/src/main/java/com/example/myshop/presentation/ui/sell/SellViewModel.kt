package com.example.myshop.presentation.ui.sell

import android.annotation.SuppressLint
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.myshop.domain.model.DailyProfits
import com.example.myshop.domain.model.Item
import com.example.myshop.domain.model.SoldEntry
import com.example.myshop.domain.repository.ShopRepository
import com.example.myshop.domain.use_cases.DeleteStockItemUseCase
import com.example.myshop.domain.use_cases.GetSellEntriesUseCase
import com.example.myshop.domain.use_cases.GetStockItemsUseCase
import com.example.myshop.utils.Cart
import com.example.myshop.utils.TimeOfSell
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class SellViewModel
@ViewModelInject constructor(
    private val shopRepository: ShopRepository,
    private val getSellEntries: GetSellEntriesUseCase,
    private  val getStockItems: GetStockItemsUseCase,
    private val deleteStockItem: DeleteStockItemUseCase,
    ) : ViewModel() {

    @SuppressLint("SimpleDateFormat")
    val format = SimpleDateFormat("dd/M/yyyy")
    private val dateToday: String = format.format(Calendar.getInstance().time)

    private var dbItems = listOf<Item>()

    init {
        prepareDbItems()
    }

    private fun prepareDbItems() {
        viewModelScope.launch {
            dbItems = getStockItems()
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
        deleteStockItem.execute(item)
    }

    fun sellCart() {
        addEntry(
            SoldEntry(
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

    private fun addEntry(entry: SoldEntry) {
        viewModelScope.launch {
            shopRepository.addEntry(entry)
        }
    }

    private fun sellEntries() = getSellEntries()

    fun addDailyProfit() {
        viewModelScope.launch {
            sellEntries().collect { listOfSoldThings ->
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