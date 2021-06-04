package com.tinkerhub.replenish.data.database.dao

import androidx.room.*
import com.tinkerhub.replenish.data.models.User

@Dao
interface UserDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)
    
    @Query("SELECT * FROM user limit 1")
    suspend fun getCurrent(): User?
    
    @Query("DELETE FROM user")
    suspend fun deleteAll()
    
    @Transaction
    suspend fun setCurrent(user: User) {
        deleteAll()
        insert(user)
    }
}