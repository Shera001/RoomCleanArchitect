package uz.crud.domain.model

data class User(
    val id: Int? = null,
    var name: String?,
    var phone: String?,
    var isFavorite: Boolean = false
)
