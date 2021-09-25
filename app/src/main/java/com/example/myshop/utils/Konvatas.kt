package com.example.myshop.utils

import androidx.room.TypeConverter
import com.example.myshop.model.Item
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

object Konvatas {

    @TypeConverter
    fun toItem(items:List<String>): String {
        return Gson().toJson(items)
    }

    @TypeConverter
    fun toList(item:String):List<String>{
        val listType = object : TypeToken<List<String>>() {}.type
       return Gson().fromJson(item,listType)
    }
}