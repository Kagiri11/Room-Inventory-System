package com.example.myshop.ui.sell

import com.example.myshop.model.Item
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class MethodsTester {
    val ushindi= Item(name="Ushindi soap",buyingPrice = 13.5,sellingPrice = 18.5)
    val supaloaf= Item(name="Supa Loaf",buyingPrice = 30.0,sellingPrice = 50.0)
    val itemOne= Item(name="Ushindi soap",buyingPrice = 13.5,sellingPrice = 18.5)
    val mkate= Item(name="Supa Loaf",buyingPrice = 30.0,sellingPrice = 50.0)

    val datasource= mutableListOf(ushindi,supaloaf,itemOne,mkate)

    //test that this function produces a set containing only different names






}