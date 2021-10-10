package com.example.myshop.presentation.ui.sellhistory

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.myshop.data.repositories.ShopRepositoryImpl
import com.example.myshop.domain.use_cases.GetSellEntriesUseCase

class SellHistoryViewModel @ViewModelInject  constructor(
   private val useCase: GetSellEntriesUseCase
):ViewModel() {
    fun getSellEntries() = useCase.invoke()
}