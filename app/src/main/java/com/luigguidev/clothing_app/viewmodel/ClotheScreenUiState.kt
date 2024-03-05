package com.luigguidev.clothing_app.viewmodel

import android.net.Uri
import androidx.compose.runtime.Immutable
import com.luigguidev.clothing_app.model.local.model.ClotheModel

@Immutable
data class ClotheScreenUiState(
    val clotheList: List<ClotheItemUiState> = emptyList(),
)

@Immutable
data class ClotheItemUiState(
    val uri: Uri ,
    val clotheModel: ClotheModel
)

//@Immutable
//data class ClotheModel(
//    val id: Int? = null,
//    val name: String,
//    val description: String,
//    val priceHigher: Double,
//    val priceUnit: Double,
//    val imagePath: String,
//    val amount: Int? = 0,
//    val totalPrice: Double? = 0.0,
//)