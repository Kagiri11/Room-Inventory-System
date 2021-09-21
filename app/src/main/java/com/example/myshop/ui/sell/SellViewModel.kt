package com.example.myshop.ui.sell
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.myshop.model.Item
import com.example.myshop.repositories.ShopRepo
import com.example.myshop.repositories.ShopRepository
import kotlinx.coroutines.launch

class SellViewModel
@ViewModelInject constructor(private val shopRepository: ShopRepository): ViewModel(){

    val items = liveData {
        emit(shopRepository.getItems())
    }

    val cartList = mutableListOf<Item>()
    var goodList2 = mutableListOf<Item>()

    val _sellCart2 = MutableLiveData<List<Item>>()
    val sellCart2 : LiveData<List<Item>> = _sellCart2


    fun addToCart(item: Item){
        cartList.add(item)
        _sellCart2.value=cartList.toList()
    }

    fun removeFromCart(item: Item){
        cartList.remove(item)
        _sellCart2.value=cartList.toList()
    }

    fun add(item: Item)=viewModelScope.launch {
        shopRepository.addItem(item)
    }

    fun deleteItem(item: Item) = viewModelScope.launch {
        shopRepository.deleteItem(item)
    }

    fun sellCart(){

    }

}