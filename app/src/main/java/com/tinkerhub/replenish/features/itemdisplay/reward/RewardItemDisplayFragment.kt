package com.tinkerhub.replenish.features.itemdisplay.reward

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.tinkerhub.replenish.R
import com.tinkerhub.replenish.common.utils.EventObserver
import com.tinkerhub.replenish.data.models.RewardItem
import com.tinkerhub.replenish.features.SharedMainViewModel
import com.tinkerhub.replenish.features.itemdisplay.ItemDisplayFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RewardItemDisplayFragment : ItemDisplayFragment() {
    
    private val sharedMainViewModel: SharedMainViewModel by activityViewModels()
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        binding.groupRequiredEventInfo.isVisible = false
        binding.groupShimmerSkeleton.isVisible = false
        
        binding.buttonText = getString(R.string.button_action_redeem)
        
        binding.labelText = getString(R.string.label_action_redeem)
        
        viewModel.buttonActionClicked.observe(viewLifecycleOwner, EventObserver {
            if (it !is RewardItem) return@EventObserver
            findNavController().navigate(
                RewardItemDisplayFragmentDirections
                    .actionRewardItemDisplayFragmentToAcknowledgementDialog(
                        imageUrlArg = getString(
                            R.string.qr_url_link,
                            getString(
                                R.string.qr_data_format,
                                it.activityId,
                                sharedMainViewModel.user.value?._id,
                                it._id
                            )
                        ),
                        titleTextArg = it.title,
                        subtitleTextArg = getString(R.string.description_claim_rewards)
                    )
            )
        })
    }
}