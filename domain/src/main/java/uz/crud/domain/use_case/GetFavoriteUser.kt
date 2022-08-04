package uz.crud.domain.use_case

import kotlinx.coroutines.flow.Flow
import uz.crud.domain.model.User
import uz.crud.domain.repository.FavoriteUsersRepository
import uz.crud.domain.repository.UserRepository
import javax.inject.Inject

class GetFavoriteUser @Inject constructor(
    private val repository: FavoriteUsersRepository
) {
    fun invoke(): Flow<List<User>> = repository.getFavoriteUsers()
}