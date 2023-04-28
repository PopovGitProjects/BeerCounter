package com.popov.dev.beercounter.domain.usecases

import com.popov.dev.beercounter.domain.models.CountItem
import com.popov.dev.beercounter.domain.repository.CountRepository

class EditCountItemUseCase(private val countRepository: CountRepository) {
    suspend fun editShopItem(countItem: CountItem) {
        countRepository.editCountItem(countItem)
    }
}