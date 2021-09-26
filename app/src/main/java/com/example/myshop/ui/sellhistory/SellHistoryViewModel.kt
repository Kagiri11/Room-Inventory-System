package com.example.myshop.ui.sellhistory

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.myshop.repositories.ShopRepository

class SellHistoryViewModel @ViewModelInject  constructor(
   private val shopRepository: ShopRepository
):ViewModel() {

    val sellEntries = liveData {
        emit(shopRepository.getSellEntries())
    }
}