package uz.crud.data.repository

import uz.crud.data.db.dao.UserDao
import uz.crud.data.db.entity.User
import uz.crud.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userDao: UserDao): UserRepository {

    suspend fun addUser(item: User) {
        userDao.insert(item)
    }

    suspend fun updateUser(item: User) {
        userDao.update(item)
    }

    override suspend fun addUser(item: uz.crud.domain.model.User) {
    }

    override suspend fun updateUser(item: uz.crud.domain.model.User) {
    }

    override suspend fun deleteUser(id: Int) {
        userDao.deleteUserById(id)
    }

    override suspend fun deleteAllUsers() {
        userDao.deleteAllUsers()
    }

    override suspend fun getUsers(): List<uz.crud.domain.model.User> = emptyList()

    override suspend fun getFavoriteUsers(): List<uz.crud.domain.model.User> = emptyList()
}