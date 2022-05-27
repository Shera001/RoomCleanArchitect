package uz.crud.domain.repository

import uz.crud.domain.model.User

interface UserRepository {

    suspend fun addUser(item: User)

    suspend fun updateUser(item: User)

    suspend fun deleteUser(id: Int)

    suspend fun deleteAllUsers()

    suspend fun getUsers(): List<User>

    suspend fun getFavoriteUsers(): List<User>
}