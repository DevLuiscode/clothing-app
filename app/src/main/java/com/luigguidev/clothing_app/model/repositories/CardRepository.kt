package com.luigguidev.clothing_app.model.repositories

import android.util.Log
import com.luigguidev.clothing_app.mappers.CardMapper
import com.luigguidev.clothing_app.model.local.model.ClotheModel
import com.luigguidev.clothing_app.model.memoryData.data.CardProvider
import com.luigguidev.clothing_app.model.memoryData.model.CardModel
import javax.inject.Inject

class CardRepository @Inject constructor (
    private val cardProvider: CardProvider
) {
    fun getAll(): MutableList<CardModel>{

        return cardProvider.getAllListCard()
    }
    fun addCard(clotheModel: ClotheModel){
        cardProvider.addCard(CardMapper.mapClotheToCard(clotheModel))
    }
    fun delete(cardModel: CardModel){
        cardProvider.deleteCard(cardModel)
    }
}