package com.tinkerhub.replenish.sources.user

import android.content.Context
import com.tinkerhub.replenish.data.managers.IDataStoreManager
import com.tinkerhub.replenish.network.ApiService

class UserRemoteSource(
    private val context: Context,
    private val apiService: ApiService,
    private val dataStoreManager: IDataStoreManager
) : IUserRemoteSource {
    
    override suspend fun requestGetUserProfile() {
    }
}

interface IUserRemoteSource {
    suspend fun requestGetUserProfile()
}