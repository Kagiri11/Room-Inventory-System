package com.example.myshop.domain.use_cases

import com.example.myshop.domain.repository.ShopRepository
import javax.inject.Inject

class GetStockItemsUseCase @Inject constructor(
    private val repository: ShopRepository
) {
    suspend operator fun invoke() = repository.getItems()
}