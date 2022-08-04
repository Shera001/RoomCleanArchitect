package uz.crud.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uz.crud.data.db.dao.UserDao
import uz.crud.data.db.entity.UserDto
import uz.crud.data.mapper.toUsers
import uz.crud.domain.model.User
import uz.crud.domain.repository.FavoriteUsersRepository
import javax.inject.Inject

class FavoriteUsersRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : FavoriteUsersRepository {

    override fun getFavoriteUsers(): Flow<List<User>> {
        return userDao.getFavoriteUsers().map { value: List<UserDto> ->
            value.toUsers()
        }
    }
}