package uz.crud.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uz.crud.data.db.dao.UserDao
import uz.crud.data.db.entity.UserDto
import uz.crud.data.mapper.toUserDto
import uz.crud.data.mapper.toUsers
import uz.crud.domain.model.User
import uz.crud.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {

    override suspend fun addUser(item: User) {
        userDao.insert(item.toUserDto())
    }

    override suspend fun updateUser(item: User) {
        userDao.update(item.toUserDto())
    }

    override suspend fun deleteUser(id: Int) {
        userDao.deleteUserById(id)
    }

    override suspend fun deleteAllUsers() {
        userDao.deleteAllUsers()
    }

    override suspend fun getUsers(): Flow<List<User>> =
        userDao.getAllUsers().map { value: List<UserDto> ->
            value.toUsers()
        }
}