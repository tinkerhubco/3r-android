package com.tinkerhub.replenish.network

import com.tinkerhub.replenish.data.models.EventItem
import com.tinkerhub.replenish.data.models.RewardItem
import com.tinkerhub.replenish.data.models.User
import com.tinkerhub.replenish.network.responses.ActivitiesResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    
    @GET("api/users/me")
    suspend fun getSelf(): Response<User>
    
    @GET("api/users/me/vouchers")
    suspend fun getRewards(
        @Query("type") type: String? = null
    ): Response<List<RewardItem>>

    @GET("api/activities")
    suspend fun getAllActivities(): Response<ActivitiesResponse>
    
    @GET("api/activities/{activityId}")
    suspend fun getActivity(
        @Path("activityId") activityId: String
    ): Response<EventItem>
    
    @POST("api/activities/{activityId}/join")
    @FormUrlEncoded
    suspend fun joinActivity(
        @Path("activityId") activityId: String,
        @Field("userId") userId: String
    )
    
    @POST("api/activities/{activityId}/claim-vouchers")
    @FormUrlEncoded
    suspend fun claimActivityPoints(
        @Path("activityId") activityId: String,
        @Field("userId") userId: String,
        @Field("voucherId") voucherId: String
    ): Response<User>
}