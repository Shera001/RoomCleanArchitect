package uz.crud.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import uz.crud.data.db.entity.User

@Dao
interface UserDao {

    @Insert(onConflict = REPLACE)
    suspend fun insert(item: User)

    @Update
    suspend fun update(item: User)

    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<User>

    @Query("SELECT * FROM users WHERE isFavorite='true'")
    suspend fun getFavoriteUsers(): List<User>

    @Query("DELETE FROM users WHERE id=:userId")
    suspend fun deleteUserById(userId: Int)

    @Query("DELETE FROM users")
    suspend fun deleteAllUsers()
}