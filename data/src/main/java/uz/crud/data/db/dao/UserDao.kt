package uz.crud.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import uz.crud.data.db.entity.UserDto

@Dao
interface UserDao {

    @Insert(onConflict = REPLACE)
    suspend fun insert(item: UserDto)

    @Update(entity = UserDto::class)
    suspend fun update(item: UserDto)

    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<UserDto>>

    @Query("SELECT * FROM users WHERE isFavorite='1'")
    fun getFavoriteUsers(): Flow<List<UserDto>>

    @Query("DELETE FROM users WHERE id=:userId")
    suspend fun deleteUserById(userId: Int)

    @Query("DELETE FROM users")
    suspend fun deleteAllUsers()
}