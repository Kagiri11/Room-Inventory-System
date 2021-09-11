package com.example.myshop.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stock_table")
data class Stock(
    @PrimaryKey(autoGenerate = true)
    val stockId:Int,
    val stockItems:List<Item>,
    val stockBuyingPrice:Double,
    val stockSellingPrice:Double,
)
