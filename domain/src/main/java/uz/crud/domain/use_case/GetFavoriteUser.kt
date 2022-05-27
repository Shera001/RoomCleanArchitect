package uz.crud.domain.use_case

import uz.crud.domain.model.User
import uz.crud.domain.repository.UserRepository
import javax.inject.Inject

class GetFavoriteUser @Inject constructor(
    private val repository: UserRepository
) {

    suspend fun invoke(): List<User> = repository.getFavoriteUsers()
}