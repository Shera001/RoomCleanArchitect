package uz.crud.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.crud.data.db.dao.UserDao
import uz.crud.data.db.entity.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase:  RoomDatabase(){

    abstract val userDao: UserDao

    companion object{
        const val DATABASE_NAME = "contacts.db"
    }
}