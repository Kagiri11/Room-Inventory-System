package com.example.myshop.domain.use_cases

import com.example.myshop.domain.model.SoldEntry
import com.example.myshop.domain.repository.ShopRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSellEntriesUseCase @Inject constructor(
    private val repository: ShopRepository
) {
    operator fun invoke(): Flow<List<SoldEntry>> = repository.getSellEntries()
}