package com.example.myshop.ui.stock

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myshop.model.Item
import com.example.myshop.repository.ShopRepository
import kotlinx.coroutines.launch


class AddStockViewModel
@ViewModelInject constructor(private val repository: ShopRepository): ViewModel() {

    fun addItems(stock:List<Item>) = viewModelScope.launch {
        for (item in stock){
            repository.addItem(item)
        }
    }
}