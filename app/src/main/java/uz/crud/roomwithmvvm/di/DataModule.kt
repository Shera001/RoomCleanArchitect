package uz.crud.roomwithmvvm.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import uz.crud.data.db.AppDatabase
import uz.crud.data.db.dao.UserDao
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideUserDao(database: AppDatabase): UserDao = database.userDao
}