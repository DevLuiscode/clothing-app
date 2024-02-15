package com.luigguidev.clothing_app.mappers

import androidx.camera.core.TorchState.State
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.luigguidev.clothing_app.model.local.entity.ClotheEntity
import com.luigguidev.clothing_app.model.local.model.ClotheModel
@Immutable
object ClotheMapper {

    fun mapEntityToModel(clotheEntity: ClotheEntity):ClotheModel{
        return ClotheModel(
            id = clotheEntity.id,
            name = clotheEntity.name,
            description = clotheEntity.description,
            priceHigher = clotheEntity.priceHigher,
            priceUnit = clotheEntity.priceUnit,
            imagePath = clotheEntity.image
        )
    }

    fun mapModelToEntity(clotheModel: ClotheModel):ClotheEntity{
        if (clotheModel.id!=null){
            return ClotheEntity(
                id = clotheModel.id,
                name = clotheModel.name,
                description = clotheModel.description,
                priceUnit = clotheModel.priceUnit,
                priceHigher = clotheModel.priceHigher,
                image = clotheModel.imagePath
            )
        }else{
            return ClotheEntity(
                name = clotheModel.name,
                description = clotheModel.description,
                priceUnit = clotheModel.priceUnit,
                priceHigher = clotheModel.priceHigher,
                image = clotheModel.imagePath
            )
        }
    }
}