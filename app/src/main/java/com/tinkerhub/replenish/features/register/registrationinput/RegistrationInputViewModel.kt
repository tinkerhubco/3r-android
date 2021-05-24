package com.tinkerhub.replenish.features.register.registrationinput

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationInputViewModel @Inject constructor(): ViewModel() {

    var inputKey = ""
}