package com.tinkerhub.replenish.features.itemdisplay

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tinkerhub.replenish.common.utils.Event
import com.tinkerhub.replenish.data.interfaces.ItemDisplayItem
import com.tinkerhub.replenish.data.models.EventItem
import com.tinkerhub.replenish.sources.activity.IActivityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ItemDisplayViewModel @Inject constructor(
    private val activityRepository: IActivityRepository
) : ViewModel() {
    
    val buttonActionClicked = MutableLiveData<Event<ItemDisplayItem>>()
    val itemDisplay = MutableLiveData<ItemDisplayItem>()
    
    suspend fun getEventDetails(activityId: String) {
        itemDisplay.value = EventItem.getDefault()
        itemDisplay.value = activityRepository.getActivity(activityId)
    }
    
    suspend fun joinEvent(eventItem: EventItem) {
        activityRepository.joinActivity(eventItem._id)?.let {
            itemDisplay.value = activityRepository.getActivity(it._id)
        }
    }
    
    fun onButtonActionClicked() {
        itemDisplay.value?.let {
            buttonActionClicked.value = Event(it)
        }
    }
}