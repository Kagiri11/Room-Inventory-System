package com.example.myshop.ui.sellhistory

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.myshop.data.repositories.ShopRepository
import com.example.myshop.model.SellEntry

class SellHistoryViewModel @ViewModelInject  constructor(
   private val shopRepository: ShopRepository
):ViewModel() {
    val listOfEntries = listOf<SellEntry>()
    val sellEntries = liveData {
        emit(shopRepository.getSellEntries())
    }




}