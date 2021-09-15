package com.example.myshop.ui.sellFragment

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.myshop.model.Item
import com.example.myshop.repository.ShopRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class SellViewModel
@ViewModelInject constructor(private val shopRepository: ShopRepository): ViewModel(){

     val itemCount = liveData {
         emit(shopRepository.getItems().count())
     }

    val items = liveData {
        emit(shopRepository.getItems())
    }

    val itemOne = Item(20,"Item One",3.5, 5.0)
    val itemTwo = Item(13,"Item two",3.5, 5.0)

    init {
        add(itemOne)
        add(itemTwo)
    }

    fun add(item: Item)=viewModelScope.launch {
        shopRepository.addItem(item)
    }





}