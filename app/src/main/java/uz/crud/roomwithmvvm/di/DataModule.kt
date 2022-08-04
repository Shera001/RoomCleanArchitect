package uz.crud.roomwithmvvm.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import uz.crud.data.db.AppDatabase
import uz.crud.data.db.dao.UserDao

@Module
class DataModule {

    @Provides
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    fun provideUserDao(database: AppDatabase): UserDao = database.userDao
}