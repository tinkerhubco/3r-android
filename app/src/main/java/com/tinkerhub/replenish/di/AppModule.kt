package com.tinkerhub.replenish.di

import android.content.Context
import android.net.TrafficStats
import androidx.room.Room
import com.tinkerhub.replenish.R
import com.tinkerhub.replenish.common.Constants.DataStoreKey
import com.tinkerhub.replenish.data.database.EcoHeroDb
import com.tinkerhub.replenish.data.managers.DataStoreManager
import com.tinkerhub.replenish.data.managers.DatabaseManager
import com.tinkerhub.replenish.data.managers.IDataStoreManager
import com.tinkerhub.replenish.data.managers.IDatabaseManager
import com.tinkerhub.replenish.network.ApiService
import com.tinkerhub.replenish.sources.activity.ActivityRemoteSource
import com.tinkerhub.replenish.sources.activity.ActivityRepository
import com.tinkerhub.replenish.sources.activity.IActivityRemoteSource
import com.tinkerhub.replenish.sources.activity.IActivityRepository
import com.tinkerhub.replenish.sources.rewards.IRewardRemoteSource
import com.tinkerhub.replenish.sources.rewards.IRewardRepository
import com.tinkerhub.replenish.sources.rewards.RewardRemoteSource
import com.tinkerhub.replenish.sources.rewards.RewardRepository
import com.tinkerhub.replenish.sources.user.IUserRemoteSource
import com.tinkerhub.replenish.sources.user.IUserRepository
import com.tinkerhub.replenish.sources.user.UserRemoteSource
import com.tinkerhub.replenish.sources.user.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {
    
    @Provides
    @Singleton
    fun provideApiService(
        @ApplicationContext context: Context,
        dataStoreManager: IDataStoreManager
    ): ApiService {
        val authTokenInterceptor = Interceptor { chain ->
            val requestBuilder =
                chain.request().newBuilder().addHeader("Accept", "application/json")
            
            val accessToken = runBlocking {
                dataStoreManager.getString(DataStoreKey.ACCESS_TOKEN)
            }
            
            if (accessToken.isNotEmpty()) {
                requestBuilder.addHeader("Authorization", "Bearer $accessToken")
            }
            
            chain.proceed(requestBuilder.build())
        }
        
        val client = OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(0, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.MINUTES)
            .addInterceptor(authTokenInterceptor)
            .addInterceptor { chain ->
                TrafficStats.setThreadStatsTag(10000)
                chain.proceed(chain.request())
            }
            .build()
        
        return Retrofit.Builder()
            .baseUrl(context.getString(R.string.base_url))
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
    
    @Provides
    @Singleton
    fun provideDataStoreManager(
        @ApplicationContext context: Context
    ): IDataStoreManager {
        return DataStoreManager(context)
    }
    
    @Provides
    @Singleton
    fun provideEcoHeroDb(
        @ApplicationContext context: Context
    ): EcoHeroDb {
        return Room.databaseBuilder(context, EcoHeroDb::class.java, "ecohero.db")
            .fallbackToDestructiveMigration().build()
    }
    
    @Provides
    @Singleton
    fun provideDatabaseManager(
        ecoHeroDb: EcoHeroDb
    ): IDatabaseManager {
        return DatabaseManager(ecoHeroDb)
    }
    
    @Provides
    @Singleton
    fun provideUserRemoteSource(
        @ApplicationContext context: Context,
        apiService: ApiService
    ): IUserRemoteSource {
        return UserRemoteSource(context, apiService)
    }
    
    @Provides
    @Singleton
    fun provideUserRepository(
        userRemoteSource: IUserRemoteSource,
        databaseManager: IDatabaseManager,
        dataStoreManager: IDataStoreManager
    ): IUserRepository {
        return UserRepository(
            userRemoteSource,
            databaseManager,
            dataStoreManager
        )
    }
    
    @Provides
    @Singleton
    fun provideActivityRemoteSource(
        @ApplicationContext context: Context,
        apiService: ApiService
    ): IActivityRemoteSource {
        return ActivityRemoteSource(context, apiService)
    }
    
    @Provides
    @Singleton
    fun provideActivityRepository(
        activityRemoteSource: IActivityRemoteSource
    ): IActivityRepository {
        return ActivityRepository(activityRemoteSource)
    }
    
    @Provides
    @Singleton
    fun provideRewardRemoteSource(
        @ApplicationContext context: Context,
        apiService: ApiService
    ): IRewardRemoteSource {
        return RewardRemoteSource(context, apiService)
    }
    
    @Provides
    @Singleton
    fun provideRewardRepository(
        rewardRemoteSource: IRewardRemoteSource
    ): IRewardRepository {
        return RewardRepository(rewardRemoteSource)
    }
}
