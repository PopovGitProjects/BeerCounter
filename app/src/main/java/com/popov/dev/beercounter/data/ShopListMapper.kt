package com.popov.dev.beercounter.data

import com.popov.dev.beercounter.data.base.CountItemDbModel
import com.popov.dev.beercounter.domain.models.CountItem

class ShopListMapper {
    fun mapEntityToDbModel(shopItem: CountItem) = CountItemDbModel(
        id = shopItem.id,
        name = shopItem.name,
        count = shopItem.count,
        enabled = shopItem.enabled
    )

    fun mapDbModelToEntity(countItemDbModel: CountItemDbModel) = CountItem(
        id = countItemDbModel.id,
        name = countItemDbModel.name,
        count = countItemDbModel.count,
        enabled = countItemDbModel.enabled
    )

    fun mapListDbModelToListEntity(list: List<CountItemDbModel>) = list.map {
        mapDbModelToEntity(it)
    }

}