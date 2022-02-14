package com.kagiri.myshop.presentation.ui.sellhistory

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.kagiri.myshop.domain.use_cases.GetSellEntriesUseCase

class SellHistoryViewModel @ViewModelInject  constructor(
   private val useCase: GetSellEntriesUseCase
):ViewModel() {
    fun getSellEntries() = useCase.invoke()
}