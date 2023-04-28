package com.popov.dev.beercounter.domain.repository

import androidx.lifecycle.LiveData
import com.popov.dev.beercounter.domain.models.CountItem

interface CountRepository {
    fun getCountList(): LiveData<List<CountItem>>
    suspend fun getCountItem(countItemId: Int): CountItem
    suspend fun addCountItem(countItem: CountItem)
    suspend fun editCountItem(countItem: CountItem)
    suspend fun deleteCountItem(countItem: CountItem)
}