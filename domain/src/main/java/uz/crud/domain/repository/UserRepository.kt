package uz.crud.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.crud.domain.model.User

interface UserRepository {

    suspend fun addUser(item: User)

    suspend fun updateUser(item: User)

    suspend fun deleteUser(id: Int)

    suspend fun deleteAllUsers()

    suspend fun getUsers(): Flow<List<User>>
}