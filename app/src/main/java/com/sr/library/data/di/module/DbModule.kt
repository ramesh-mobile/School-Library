package com.sr.library.data.di.module

import android.app.Application
import androidx.room.Room
import com.sr.library.data.database.LibraryDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by ramesh on 17-10-2021
 */
@Module
class DbModule(var application: Application) {

    @Singleton
    @Provides
    fun provideDatabase(): LibraryDatabase {
        return Room.databaseBuilder(
            application,
            LibraryDatabase::class.java,
            "StudentDetailsTables"
        ).fallbackToDestructiveMigration().build()
    }
}
