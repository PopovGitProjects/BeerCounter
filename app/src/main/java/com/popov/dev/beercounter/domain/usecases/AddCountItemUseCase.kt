package com.popov.dev.beercounter.domain.usecases

import com.popov.dev.beercounter.domain.models.CountItem
import com.popov.dev.beercounter.domain.repository.CountRepository

class AddCountItemUseCase(private val CountRepository: CountRepository) {
    suspend fun addShopItem(countItem: CountItem){
        CountRepository.addCountItem(countItem)
    }
}