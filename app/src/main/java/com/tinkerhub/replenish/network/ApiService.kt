package com.tinkerhub.replenish.network

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    
    @GET("api/users/me")
    suspend fun getSelf()

    @GET("api/activities")
    suspend fun getAllActivities()
    
    @GET("api/rewards")
    suspend fun getAllRewards()
    
    @POST("api/activities/{activityId}/join")
    @FormUrlEncoded
    suspend fun joinActivity(
        @Path("activityId") activityId: Int,
        @Field("userId") userId: String
    )
    
    @POST("api/activities/{activityId}/points")
    @FormUrlEncoded
    suspend fun claimActivityPoints(
        @Path("activityId") activityId: Int,
        @Field("userId") userId: String
    )
}