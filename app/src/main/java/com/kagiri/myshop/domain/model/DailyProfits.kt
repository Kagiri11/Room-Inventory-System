package com.kagiri.myshop.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "daily_profits")
data class DailyProfits(
    @PrimaryKey
    val date : String,
    val profitAmount : Double
)
