package com.example.myshop.ui.sellFragment

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
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

    val itemOne = Item(20,"Heart cake",6.5, 10.0)
    val itemTwo = Item(13,"Kiberiti",2.0, 5.0)
    val itemThree = Item(19,"Fritos",100.0, 155.0)

    val cartList = mutableListOf<Item>()
    val _sellCart2 = MutableLiveData<List<Item>>()
    val sellCart2 : LiveData<List<Item>> = _sellCart2

    init {
        add(itemOne)
        add(itemTwo)
        add(itemThree)
    }

    fun addToCart(item: Item){
        cartList.add(item)
        _sellCart2.postValue(cartList.toList())
    }

    fun add(item: Item)=viewModelScope.launch {
        shopRepository.addItem(item)
    }

}