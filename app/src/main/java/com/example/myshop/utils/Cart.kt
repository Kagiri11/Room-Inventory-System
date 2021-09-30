package com.example.myshop.utils

import com.example.myshop.model.Item

object Cart {
    val space = mutableListOf<Item>()

    fun addItemToCart(item:Item): Cart {
        space.add(item)
        return this
    }

    fun removeItemFromCart(item: Item): Cart {
        space.remove(item)
        return this
    }

    fun refreshCart(){
        space.clear()
    }

}