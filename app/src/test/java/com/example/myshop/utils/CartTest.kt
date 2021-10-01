package com.example.myshop.utils

import com.example.myshop.model.Item
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CartTest {

    val spaceSize = Cart.space.size

    @Test
    fun `space is empty after addition should return false`(){
        val testingItem = Item(name = "Testing Item", buyingPrice = 3.5,sellingPrice = 7.8)
        val testSpace= Cart.space
        Cart.addItemToCart(testingItem)
        assertThat(testSpace).isNotEmpty()
    }

    @Test
    fun `space size is not incremented after addition returns false`(){
        val testingItem = Item(name = "Testing Item", buyingPrice = 3.5,sellingPrice = 7.8)
        Cart.addItemToCart(testingItem)
        assertThat(Cart.space.size).isGreaterThan(spaceSize)
    }

    @Test
    fun `removal of an item from space reduces size`(){
        val testingItem = Item(name = "Testing Item", buyingPrice = 3.5,sellingPrice = 7.8)
        Cart.space.add(testingItem)
        Cart.removeItemFromCart(testingItem)
        assertThat(Cart.space.size).isEqualTo(spaceSize)
    }

    @Test
    fun `removal of an item on an empty space returns false`(){
        val testingItem = Item(name = "Testing Item", buyingPrice = 3.5,sellingPrice = 7.8)
        if(Cart.space.isEmpty()){
            assertThat( Cart.removeItemFromCart(testingItem)).isEqualTo("Cart is empty")
        }
    }

    @Test
    fun `refreshing the cart removes everything`(){
        val testingItem = Item(name = "Testing Item", buyingPrice = 3.5,sellingPrice = 7.8)
        Cart.space.add(testingItem)
        Cart.refreshCart()
        assertThat(Cart.space.size).isEqualTo(0)
    }
}