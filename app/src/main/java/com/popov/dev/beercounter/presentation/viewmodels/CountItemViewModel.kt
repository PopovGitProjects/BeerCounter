package com.popov.dev.beercounter.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.popov.dev.beercounter.data.CountRepositoryImpl
import com.popov.dev.beercounter.domain.models.CountItem
import com.popov.dev.beercounter.domain.usecases.AddCountItemUseCase
import com.popov.dev.beercounter.domain.usecases.EditCountItemUseCase
import com.popov.dev.beercounter.domain.usecases.GetCountItemUseCase
import kotlinx.coroutines.launch

class CountItemViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = CountRepositoryImpl(application)
    private val getCountItemUseCase = GetCountItemUseCase(repository)
    private val addCountItemUseCase = AddCountItemUseCase(repository)
    private val editCountItemUseCase = EditCountItemUseCase(repository)

    private val _errorInputName = MutableLiveData<Boolean>()
    private val _errorInputCount = MutableLiveData<Boolean>()
    val errorInputName = _errorInputName as LiveData<Boolean>
    val errorInputCount = _errorInputCount as LiveData<Boolean>

    private val _countItemLiveData = MutableLiveData<CountItem>()
    val countItemLiveData = _countItemLiveData as LiveData<CountItem>

    private val _needToCloseScreen = MutableLiveData<Unit>()
    val needToCloseScreen = _needToCloseScreen as LiveData<Unit>


    fun getCountItem(countItemId: Int) {
        viewModelScope.launch {
            val item = getCountItemUseCase.getShopItem(countItemId)
            _countItemLiveData.value = item
        }
    }

    fun addShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldValid = validateInput(name, count)
        if (fieldValid) {
            viewModelScope.launch {
                val shopItem = CountItem(name, count, true)
                addCountItemUseCase.addShopItem(shopItem)
                setNeedToCloseScreen()
            }
        }
    }

    fun editShopItem(inputName: String?, inputCount: String?) { //TODO: not correct edit
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldValid = validateInput(name, count)
        if (fieldValid) {
            _countItemLiveData.value?.let {
                viewModelScope.launch {
                    val item = it.copy(name = name, count = count)
                    editCountItemUseCase.editShopItem(item)
                    setNeedToCloseScreen()
                }
            }
        }
    }

    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }

    private fun parseCount(inputCount: String?): Int {
        return try {
            inputCount?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    private fun validateInput(name: String, count: Int): Boolean {
        var result = true
        if (name.isBlank()) {
            result = false
            _errorInputName.value = true
        }
        if (count <= 0) {
            result = false
            _errorInputCount.value = true
        }
        return result
    }

    fun resetErrorInputName() {
        _errorInputName.value = false // TODO: Ошибка не уходит, разобраться!
    }

    fun resetErrorInputCount() {
        _errorInputCount.value = false // TODO: Ошибка не уходит, разобраться!
    }

    private fun setNeedToCloseScreen() {
        _needToCloseScreen.value = Unit
    }
}