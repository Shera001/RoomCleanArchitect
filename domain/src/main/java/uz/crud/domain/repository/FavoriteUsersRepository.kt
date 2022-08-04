package uz.crud.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.crud.domain.model.User

interface FavoriteUsersRepository {
    fun getFavoriteUsers(): Flow<List<User>>
}