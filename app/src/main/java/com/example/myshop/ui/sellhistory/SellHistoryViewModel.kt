package com.example.myshop.ui.sellhistory

import android.annotation.SuppressLint
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.myshop.data.repositories.ShopRepository
import com.example.myshop.model.SellEntry
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class SellHistoryViewModel @ViewModelInject  constructor(
   private val shopRepository: ShopRepository
):ViewModel() {
    @SuppressLint("SimpleDateFormat")
    val format = SimpleDateFormat("dd/M/yyyy")
    private val dateToday: String = format.format(Calendar.getInstance().time)
    var listOfEntries = listOf<SellEntry>()
    private val _dailyProfit = MutableLiveData<Double>()
    val dailyProfit: LiveData<Double> = _dailyProfit
    init {
        initializeSellEntries()

        println("SellHistoryViewModel has been initialised")
    }

    private fun initializeSellEntries() {
        viewModelScope.launch {
            listOfEntries = shopRepository.getSellEntries()
            _dailyProfit.value=listOfEntries.filter { it.timeSold.contains(dateToday) }.sumOf { it.totalProfit }
        }
    }



    val sellEntries = liveData {
        emit(shopRepository.getSellEntries())
    }

}