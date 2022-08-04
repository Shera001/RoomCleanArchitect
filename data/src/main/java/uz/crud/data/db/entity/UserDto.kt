package uz.crud.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserDto(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String?,
    val phone: String?,
    val isFavorite: Boolean = false
)