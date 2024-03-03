package com.luigguidev.clothing_app.model.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.luigguidev.clothing_app.model.local.dao.ClotheDao
import com.luigguidev.clothing_app.model.local.entity.ClotheEntity

@Database(
    entities = [
        ClotheEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun clotheDao(): ClotheDao
}
