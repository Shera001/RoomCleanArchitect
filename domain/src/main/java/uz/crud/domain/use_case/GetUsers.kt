package uz.crud.domain.use_case

import kotlinx.coroutines.flow.Flow
import uz.crud.domain.model.User
import uz.crud.domain.repository.UserRepository
import javax.inject.Inject

class GetUsers @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(): Flow<List<User>> = repository.getUsers()
}