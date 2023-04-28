package com.popov.dev.beercounter.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.popov.dev.beercounter.domain.models.CountItem
import com.popov.dev.beercounter.data.CountRepositoryImpl
import com.popov.dev.beercounter.domain.usecases.DeleteCountItemUseCase
import com.popov.dev.beercounter.domain.usecases.EditCountItemUseCase
import com.popov.dev.beercounter.domain.usecases.GetCountListUseCase
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = CountRepositoryImpl(application)
    private val getCountUseCase = GetCountListUseCase(repository)
    private val deleteCountItemUseCase = DeleteCountItemUseCase(repository)
    private val editCountItemUseCase = EditCountItemUseCase(repository)

    val countListLiveData = getCountUseCase.getShopList()

    fun deleteCountItem(countItem: CountItem) {
        viewModelScope.launch {
            deleteCountItemUseCase.deleteShopItem(countItem)
        }
    }

    fun changeEnableState(countItem: CountItem) {
        viewModelScope.launch {
            when (countItem.enabled) {
                true -> {
                    editCountItemUseCase.editShopItem(countItem.copy(enabled = false))
                }

                false -> {
                    editCountItemUseCase.editShopItem(countItem.copy(enabled = true))
                }
            }
        }
    }
}