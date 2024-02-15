package com.luigguidev.clothing_app.di

import android.app.Application
import androidx.room.Room
import com.luigguidev.clothing_app.model.local.AppDatabase
import com.luigguidev.clothing_app.model.local.dao.ClotheDao
import com.luigguidev.clothing_app.model.memoryData.data.CardProvider
import com.luigguidev.clothing_app.model.repositories.CardRepository
import com.luigguidev.clothing_app.model.repositories.ClotheRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideDb(application: Application): AppDatabase {
        return Room.databaseBuilder(
            context = application,
            AppDatabase::class.java,
            name = "clothing-store"
        ).build()
    }

    @Provides
    @Singleton
    fun provideClotheDao(appDatabase: AppDatabase): ClotheDao {
        return appDatabase.clotheDao()
    }

    @Provides
    @Singleton
    fun provideClotheRepository(clotheDao: ClotheDao): ClotheRepository {
        return ClotheRepository(clotheDao)
    }


}