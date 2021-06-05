package com.tinkerhub.replenish.sources.user

import com.tinkerhub.replenish.common.Constants.DataStoreKey.ACCESS_TOKEN
import com.tinkerhub.replenish.data.managers.IDataStoreManager
import com.tinkerhub.replenish.data.managers.IDatabaseManager
import com.tinkerhub.replenish.data.models.User
import com.tinkerhub.replenish.network.Result

class UserRepository(
    private val remoteSource: IUserRemoteSource,
    private val databaseManager: IDatabaseManager,
    private val dataStoreManager: IDataStoreManager
) : IUserRepository {
    
    override suspend fun login(): User? {
        dataStoreManager.putString(
            ACCESS_TOKEN,
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjYwYjc2Mjc" +
                "zM2ExOGQ5NTQyMjUxOTgzNiJ9._EH4NSuTm8GavZCdITN7pAx6KBGB5t5H0qi1OG9q5Ck"
        )
        return getUserProfile()
    }
    
    override suspend fun getUserProfile(): User? {
        val userResult = remoteSource.requestGetUserProfile()
        
        return if (userResult is Result.Success && userResult.data != null) {
            databaseManager.setUser(userResult.data)
            userResult.data
        } else {
            databaseManager.getCurrentUser()
        }
    }
    
    override suspend fun getCurrentUser(): User? {
        return databaseManager.getCurrentUser()
    }
}

interface IUserRepository {
    suspend fun login(): User?
    suspend fun getUserProfile(): User?
    suspend fun getCurrentUser(): User?
}