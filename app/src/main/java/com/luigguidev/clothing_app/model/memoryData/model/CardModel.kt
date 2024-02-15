package com.luigguidev.clothing_app.model.memoryData.model


data class CardModel(
    val id: Int ? = null,
    val name: String,
    val description: String,
    val priceHigher: Double,
    val priceUnit: Double,
    val image: String,
    val amount: Int,
    val priceTotal: Double
)



