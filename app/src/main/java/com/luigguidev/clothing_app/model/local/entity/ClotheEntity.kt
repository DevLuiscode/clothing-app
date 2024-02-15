package com.luigguidev.clothing_app.model.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "clothe_table")
data class ClotheEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val description: String,
    val priceUnit: Double,
    val priceHigher: Double,
    val image: String
)
