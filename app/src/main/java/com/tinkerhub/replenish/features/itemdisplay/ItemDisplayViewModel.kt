package com.tinkerhub.replenish.features.itemdisplay

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tinkerhub.replenish.common.utils.Event
import com.tinkerhub.replenish.data.interfaces.ItemDisplayItem
import com.tinkerhub.replenish.data.models.EventItem
import com.tinkerhub.replenish.sources.activity.IActivityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ItemDisplayViewModel @Inject constructor(
    private val activityRepository: IActivityRepository
) : ViewModel() {
    
    val buttonActionClicked = MutableLiveData<Event<ItemDisplayItem>>()
    val itemDisplay = MutableLiveData<ItemDisplayItem>()
    
    fun getEventDetails(activityId: String) {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                itemDisplay.value = EventItem.getDefault()
                itemDisplay.value = activityRepository.getActivity(activityId)
            }
        }
    }
    
    fun onButtonActionClicked() {
        itemDisplay.value?.let {
            buttonActionClicked.value = Event(it)
        }
    }
}