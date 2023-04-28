package com.popov.dev.beercounter.data.base

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shop_items")
data class CountItemDbModel(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val count: Int,
    val enabled: Boolean
)