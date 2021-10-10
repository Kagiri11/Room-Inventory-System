package com.example.myshop.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sells_table")
data class SoldEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val soldItems: List<String>,
    val totalProfit: Double,
    val timeSold: String,
    val userId: Int? = null
)
