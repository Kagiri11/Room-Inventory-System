package com.example.myshop.presentation.ui.stock

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myshop.domain.model.Item
import com.example.myshop.data.repositories.ShopRepositoryImpl
import kotlinx.coroutines.launch


class AddStockViewModel
@ViewModelInject constructor(private val repository: ShopRepositoryImpl): ViewModel() {

    fun addItems(stock:List<Item>) = viewModelScope.launch {
        for (item in stock){
            repository.addItem(item)
        }
    }
}