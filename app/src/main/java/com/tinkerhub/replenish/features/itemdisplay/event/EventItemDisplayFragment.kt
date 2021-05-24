package com.tinkerhub.replenish.features.itemdisplay.event

import android.os.Bundle
import android.view.View
import com.tinkerhub.replenish.R
import com.tinkerhub.replenish.features.itemdisplay.ItemDisplayFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventItemDisplayFragment : ItemDisplayFragment() {
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        binding.buttonItemAction.text = getString(R.string.button_action_join)
        binding.textviewItemActionLabel.text = getString(R.string.label_action_join)
    }
}