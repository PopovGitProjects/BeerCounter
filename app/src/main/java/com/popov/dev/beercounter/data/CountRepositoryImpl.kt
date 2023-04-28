package com.popov.dev.beercounter.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.popov.dev.beercounter.domain.models.CountItem
import com.popov.dev.beercounter.domain.repository.CountRepository
import com.popov.shoppinglist.data.base.AppDataBase

class CountRepositoryImpl(application: Application) : CountRepository {

    private val countDao = AppDataBase.getInstance(application).countListDao()
    private val mapper = ShopListMapper()


    override fun getCountList(): LiveData<List<CountItem>> = countDao.getCountList().map {
        mapper.mapListDbModelToListEntity(it)
    }

    override suspend fun getCountItem(countItemId: Int): CountItem {
        val dbModel = countDao.getCountItem(countItemId)
        return mapper.mapDbModelToEntity(dbModel)
    }

    override suspend fun addCountItem(countItem: CountItem) {
        countDao.addCountItem(mapper.mapEntityToDbModel(countItem))
    }

    override suspend fun editCountItem(countItem: CountItem) {
        addCountItem(countItem)
    }

    override suspend fun deleteCountItem(countItem: CountItem) {
        countDao.deleteCountItem(countItem.id)
    }
}