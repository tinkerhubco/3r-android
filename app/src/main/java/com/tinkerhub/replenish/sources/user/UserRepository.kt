package com.tinkerhub.replenish.sources.user

import com.tinkerhub.replenish.data.managers.IDataStoreManager
import com.tinkerhub.replenish.data.managers.IDatabaseManager

class UserRepository(
    private val remoteSource: IUserRemoteSource,
    private val databaseManager: IDatabaseManager,
    private val dataStoreManager: IDataStoreManager
) : IUserRepository {
    
    override suspend fun getUserProfile() {
    }
}

interface IUserRepository {
    suspend fun getUserProfile()
}