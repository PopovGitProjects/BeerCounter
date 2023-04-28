package com.popov.dev.beercounter.domain.usecases

import com.popov.dev.beercounter.domain.models.CountItem
import com.popov.dev.beercounter.domain.repository.CountRepository

class GetCountItemUseCase(private val countRepository: CountRepository) {
    suspend fun getShopItem(countItemId: Int): CountItem{
        return countRepository.getCountItem(countItemId)
    }
}