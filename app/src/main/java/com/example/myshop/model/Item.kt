package com.example.myshop.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_table")
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val name:String,
    val buyingPrice:Double,
    var sellingPrice:Double,
    val profit: Double =sellingPrice-buyingPrice
)
