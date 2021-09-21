package com.example.myshop.ui.sell

import com.example.myshop.model.Item
import com.example.myshop.repositories.FakeShopRepository
import com.google.common.truth.Truth
import org.junit.Before

import org.junit.Test

class SellViewModelTest {

    private lateinit var sellViewModel : SellViewModel

    @Before
    fun setup(){
       sellViewModel = SellViewModel(FakeShopRepository())
    }

    val ushindi= Item(name="Ushindi soap",buyingPrice = 13.5,sellingPrice = 18.5)
    val supaloaf= Item(name="Supa Loaf",buyingPrice = 30.0,sellingPrice = 50.0)
    val itemOne= Item(name="Ushindi soap",buyingPrice = 13.5,sellingPrice = 18.5)
    val mkate= Item(name="Supa Loaf",buyingPrice = 30.0,sellingPrice = 50.0)

    val datasource= mutableListOf(ushindi,supaloaf,itemOne,mkate)

    @Test
    fun `test that we are getting only a set from a list of items`(){

        val result = sellViewModel.getSetFromList(datasource)
        val newSet = setOf<Item>()
        Truth.assertThat(result).isEqualTo(newSet)
    }
}