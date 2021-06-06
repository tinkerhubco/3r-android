package com.tinkerhub.replenish.features.explore

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tinkerhub.replenish.data.models.EventItem
import com.tinkerhub.replenish.sources.activity.IActivityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val activityRepository: IActivityRepository
) : ViewModel() {
    
    val eventsList = MutableLiveData<ArrayList<EventItem>>()
    val activitiesList = MutableLiveData<ArrayList<EventItem>>()
    
    init {
        viewModelScope.launch {
            loadSkeleton()
            loadActivities()
        }
    }
    
    private fun loadSkeleton() {
        val mockEventsList = arrayListOf<EventItem>()
        val mockActivitiesList = arrayListOf<EventItem>()
        for (i in 1..6) {
            val mockItem = EventItem.getDefault()
        
            mockEventsList.add(mockItem)
            mockActivitiesList.add(mockItem)
        }
    
        eventsList.value = mockEventsList
        activitiesList.value = mockActivitiesList
    }
    
    suspend fun loadActivities() {
        activityRepository.getActivities().run {
            eventsList.value = ArrayList(upcoming)
            activitiesList.value = ArrayList(trending)
        }
    }
}