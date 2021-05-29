package com.tinkerhub.replenish.features.itemdisplay.reward

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import com.tinkerhub.replenish.R
import com.tinkerhub.replenish.common.utils.EventObserver
import com.tinkerhub.replenish.data.models.RewardItem
import com.tinkerhub.replenish.features.itemdisplay.ItemDisplayFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RewardItemDisplayFragment : ItemDisplayFragment() {
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        binding.groupRequiredEventInfo.isVisible = false
        
        binding.buttonItemAction.text = getString(R.string.button_action_redeem)
        
        binding.textviewItemActionLabel.text =
            getString(
                R.string.label_action_redeem,
                viewModel.itemDisplay.value?.rewardPoints
            )
        
        viewModel.buttonActionClicked.observe(viewLifecycleOwner, EventObserver {
            if (it !is RewardItem) return@EventObserver
            Toast.makeText(context, "Redeem ${it.title} now!", Toast.LENGTH_SHORT).show()
        })
    }
}