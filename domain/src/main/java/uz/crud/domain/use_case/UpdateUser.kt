package uz.crud.domain.use_case

import uz.crud.domain.model.User
import uz.crud.domain.repository.UserRepository
import uz.crud.domain.util.Resource
import javax.inject.Inject

class UpdateUser @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(user: User) {
        user.isFavorite = !user.isFavorite
        userRepository.updateUser(item = user)
    }
}