package com.popov.dev.beercounter.domain.usecases

import androidx.lifecycle.LiveData
import com.popov.dev.beercounter.domain.models.CountItem
import com.popov.dev.beercounter.domain.repository.CountRepository

class GetCountListUseCase(private val countRepository: CountRepository) {
    fun getShopList(): LiveData<List<CountItem>>{
        return countRepository.getCountList()
    }
}