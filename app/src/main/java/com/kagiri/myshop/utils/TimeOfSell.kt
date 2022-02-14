package com.kagiri.myshop.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object TimeOfSell {
    @SuppressLint("SimpleDateFormat")
    fun stamp(): String {
        return SimpleDateFormat("hh:mm dd/M/yyyy").format(Calendar.getInstance().time)
    }
}