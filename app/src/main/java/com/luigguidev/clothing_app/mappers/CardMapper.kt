package com.luigguidev.clothing_app.mappers

import com.luigguidev.clothing_app.model.local.model.ClotheModel
import com.luigguidev.clothing_app.model.memoryData.model.CardModel

object CardMapper {
    fun mapClotheToCard(clotheModel: ClotheModel): CardModel {
        return CardModel(
            id = clotheModel.id,
            name = clotheModel.name,
            description = clotheModel.description,
            priceHigher = clotheModel.priceHigher,
            priceUnit = clotheModel.priceUnit,
            priceTotal = 0.0,
            image = clotheModel.imagePath,
            amount = 0
        )
    }
}