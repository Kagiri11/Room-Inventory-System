package com.example.myshop.ui.sellhistory

import android.annotation.SuppressLint
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.myshop.data.repositories.ShopRepository
import com.example.myshop.model.SellEntry
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class SellHistoryViewModel @ViewModelInject  constructor(
   private val shopRepository: ShopRepository
):ViewModel() {


    fun getSellEntries()= shopRepository.getSellEntries()
}