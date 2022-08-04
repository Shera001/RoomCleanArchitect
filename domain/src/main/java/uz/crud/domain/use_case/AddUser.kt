package uz.crud.domain.use_case

import uz.crud.domain.model.User
import uz.crud.domain.repository.UserRepository
import uz.crud.domain.util.Resource
import javax.inject.Inject

class AddUser @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(user: User, isUpdate: Boolean): Resource {
        if (user.name.isNullOrEmpty()) {
            return Resource.Empty.NameEmpty
        }

        if (user.phone.isNullOrEmpty()) {
            return Resource.Empty.NumberEmpty
        }

        if (isUpdate) {
            userRepository.updateUser(user)
        } else {
            userRepository.addUser(user)
        }

        return Resource.Success
    }
}