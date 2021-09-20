package com.example.myshop.ui.sell

import com.example.myshop.model.Item
import com.example.myshop.repositories.FakeShopRepository
import org.junit.Before

import org.junit.Test

class SellViewModelTest {

    private lateinit var sellViewModel : SellViewModel

    @Before
    fun setup(){
       sellViewModel = SellViewModel(FakeShopRepository())
    }

    @Test
    fun `get items returns a list of items`() : List<Item>{

        val itemsList = sellViewModel.items

    }
}