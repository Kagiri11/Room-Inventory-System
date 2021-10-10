package com.example.myshop.domain.use_cases

import com.example.myshop.domain.model.Item
import com.example.myshop.domain.repository.ShopRepository
import javax.inject.Inject

class DeleteStockItemUseCase @Inject constructor(
    private val repository: ShopRepository
) {

    suspend fun execute(item: Item) = repository.deleteItem(item)

}