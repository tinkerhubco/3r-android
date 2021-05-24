package com.tinkerhub.replenish.features.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    
    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()
}