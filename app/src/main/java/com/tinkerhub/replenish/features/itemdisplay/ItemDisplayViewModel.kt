package com.tinkerhub.replenish.features.itemdisplay

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tinkerhub.replenish.common.utils.Event
import com.tinkerhub.replenish.data.interfaces.ItemDisplayItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ItemDisplayViewModel @Inject constructor() : ViewModel() {
    
    val buttonActionClicked = MutableLiveData<Event<Unit>>()
    val itemDisplay = MutableLiveData<ItemDisplayItem>()
    
    fun onButtonActionClicked() {
        buttonActionClicked.value = Event(Unit)
    }
}