package com.luigguidev.clothing_app.model.memoryData.data

import android.util.Log
import com.luigguidev.clothing_app.model.memoryData.model.CardModel
import javax.inject.Inject

class CardProvider @Inject constructor() {

    private var listCard = mutableListOf<CardModel>()

    fun getAllListCard(): MutableList<CardModel> {
        return listCard
    }

    fun addCard(cardModel: CardModel) {
        if (!listCard.contains(cardModel)) {
            listCard.add(cardModel)
        }
    }

    fun deleteCard(cardModel: CardModel) {
        if (listCard.contains(cardModel)) {
            listCard.remove(cardModel)
        }
    }


}