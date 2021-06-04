package com.tinkerhub.replenish.sources.activity

import android.content.Context
import com.tinkerhub.replenish.network.ApiService

class ActivityRemoteSource(
    private val context: Context,
    private val apiService: ApiService
) : IActivityRemoteSource {
    
    override suspend fun requestGetActivities() {
    }
    
    override suspend fun requestJoinActivity() {
    }
    
    override suspend fun requestClaimActivityPoints() {
    }
}

interface IActivityRemoteSource {
    suspend fun requestGetActivities()
    suspend fun requestJoinActivity()
    suspend fun requestClaimActivityPoints()
}