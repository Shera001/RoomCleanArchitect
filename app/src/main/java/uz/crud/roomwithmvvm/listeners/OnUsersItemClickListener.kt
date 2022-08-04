package uz.crud.roomwithmvvm.listeners

import uz.crud.domain.model.User

interface OnUsersItemClickListener {
    fun onUsersItemClick(item: User)
    fun onFavoriteClick(item: User)
}