package com.tinkerhub.replenish.features.register.registrationinput

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationInputSharedViewModel @Inject constructor() : ViewModel() {
    
    val registrationInputs = HashMap<String, Input>()
    
    class Input(
        val position: Int,
        val hint: String,
        val buttonText: String,
        val action: () -> Unit = {}
    ) {
        var value: String = ""
    }
}