package uz.crud.roomwithmvvm.di

import dagger.Module
import dagger.Provides
import uz.crud.data.db.dao.UserDao
import uz.crud.data.repository.UserRepositoryImpl
import uz.crud.domain.repository.UserRepository

@Module
class DomainModule {

    @Provides
    fun provideUserRepository(userDao: UserDao): UserRepository = UserRepositoryImpl(userDao)
}