package uz.crud.data.mapper

import android.util.Log
import uz.crud.data.db.entity.UserDto
import uz.crud.domain.model.User

fun UserDto.toUser(): User = User(
    id,
    name,
    phone,
    isFavorite
)

fun User.toUserDto(): UserDto = UserDto(
    id,
    name,
    phone,
    isFavorite
)

fun List<UserDto>.toUsers(): List<User> {
    val users = arrayListOf<User>()
    forEach {
        Log.e("TAG", "toUsers: ${it.toUser()}")
        users.add(it.toUser())
    }
    return users
}