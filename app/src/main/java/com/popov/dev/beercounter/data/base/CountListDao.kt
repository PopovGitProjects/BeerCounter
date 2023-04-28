package com.popov.dev.beercounter.data.base

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CountListDao {
    @Query("SELECT * FROM shop_items")
    fun getCountList(): LiveData<List<CountItemDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCountItem(countItemDbModel: CountItemDbModel)

    @Query("DELETE FROM shop_items WHERE id = :countItemId")
    suspend fun deleteCountItem(countItemId: Int)

    @Query("SELECT * FROM shop_items WHERE id = :countItemId LIMIT 1")
    suspend fun getCountItem(countItemId: Int): CountItemDbModel
}