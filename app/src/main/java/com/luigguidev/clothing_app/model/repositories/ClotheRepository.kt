package com.luigguidev.clothing_app.model.repositories

import com.luigguidev.clothing_app.mappers.ClotheMapper
import com.luigguidev.clothing_app.model.local.dao.ClotheDao
import com.luigguidev.clothing_app.model.local.entity.ClotheEntity
import com.luigguidev.clothing_app.model.local.model.ClotheModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ClotheRepository(
    private val clotheDao: ClotheDao
) {
    fun getAllClothe(): Flow<List<ClotheModel>> {
        val response = clotheDao.getAllClothe()
        return response.map { listClotheEntity ->
            listClotheEntity.map { clotheEntity ->
                ClotheMapper.mapEntityToModel(clotheEntity = clotheEntity)
            }
        }
    }

    suspend fun insertClothe(clotheModel: ClotheModel) {
        val responseMap = ClotheMapper.mapModelToEntity(clotheModel)
        clotheDao.insertClothe(responseMap)
    }

}