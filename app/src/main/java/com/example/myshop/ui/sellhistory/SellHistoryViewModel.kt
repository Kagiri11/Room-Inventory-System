package com.example.myshop.ui.sellhistory

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.myshop.data.repositories.ShopRepository
import com.example.myshop.model.SellEntry
import kotlinx.coroutines.launch

class SellHistoryViewModel @ViewModelInject  constructor(
   private val shopRepository: ShopRepository
):ViewModel() {
    var listOfEntries = listOf<SellEntry>()
    val dateToday = ""
    val sellEntries = liveData {
        emit(shopRepository.getSellEntries())
    }
    init {
        initializeSellEntries()
    }

    val daysProfit = liveData {
        emit(listOfEntries.filter { it.timeSold.contains(dateToday) }.sumOf { it.totalProfit })
    }

    private fun initializeSellEntries() {
        viewModelScope.launch {
            listOfEntries = shopRepository.getSellEntries()
        }
    }
}