package com.luigguidev.clothing_app.model.local.model

import androidx.compose.runtime.Immutable

@Immutable
data class ClotheModel(
    val id: Int? = null,
    val name: String,
    val description: String,
    val priceHigher: Double,
    val priceUnit: Double,
    val imagePath: String,
    val amount: Int? = 0,
    val totalPrice: Double? = 0.0,
)
