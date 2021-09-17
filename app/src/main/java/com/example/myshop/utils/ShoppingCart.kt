package com.example.myshop.utils

import com.example.myshop.model.Item

object ShoppingCart {
    val cartList = mutableListOf<Item>()

    private fun myFilterAndSumBy2(list :MutableList<Item>):MutableList<Item>{
        val cartTwo = mutableListOf<Item>()
        for(item in list){
            if(!cartTwo.contains(item)){
                cartTwo.add(item)
            }else{
                val secondValue = cartTwo.filter { it.name == item.name }.sumByDouble { it.sellingPrice } + item.sellingPrice
                val truePair = Item(id = item.id,name = item.name,sellingPrice= secondValue,buyingPrice = item.buyingPrice)
                cartTwo.removeIf { it==item }
                cartTwo.add(truePair)
            }
        }
        return cartTwo
    }

    val exposedList = myFilterAndSumBy2(cartList)
}

