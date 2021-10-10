package com.example.myshop.utils

import com.example.myshop.domain.model.Item

object Cart {
    val space = mutableListOf<Item>()

    /**
     * Not valid if;
     *  - space is empty after addition
     *  - space.size is not incremented
     */
    fun addItemToCart(item:Item){
        space.add(item)
    }

    /**
     * Not valid if;
     * - space.size remains the same
     * - space is empty
     */
    fun removeItemFromCart(item: Item):String {
        return if (space.isEmpty()){
            "Cart is empty"
        }else{
            space.remove(item)
            "$item removed"
        }
    }

    /**
     * Not valid if;
     * - space.size is not 0
     */
    fun refreshCart(){
        space.clear()
    }
}