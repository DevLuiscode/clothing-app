package com.luigguidev.clothing_app.model.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.luigguidev.clothing_app.model.local.entity.ClotheEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface ClotheDao {
    @Query("SELECT * FROM clothe_table")
    fun getAllClothe(): Flow<List<ClotheEntity>>

    @Insert
    suspend fun insertClothe(clotheEntity: ClotheEntity)
}