package com.popov.dev.beercounter.domain.usecases

import com.popov.dev.beercounter.domain.models.CountItem
import com.popov.dev.beercounter.domain.repository.CountRepository

class DeleteCountItemUseCase(private val countRepository: CountRepository) {
    suspend fun deleteShopItem(countItem: CountItem) {
        countRepository.deleteCountItem(countItem)
    }
}