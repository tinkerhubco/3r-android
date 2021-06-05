package com.tinkerhub.replenish.features.itemdisplay.rewardselector

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tinkerhub.replenish.R
import com.tinkerhub.replenish.common.utils.autoCleared
import com.tinkerhub.replenish.data.adapters.RewardItemAdapter
import com.tinkerhub.replenish.data.models.RewardItem
import com.tinkerhub.replenish.databinding.FragmentRewardSelectorBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RewardSelectorFragment : Fragment(), RewardItemAdapter.RewardItemListener {
    
    companion object {
        const val EVENT_ID_ARG = "eventIdArg"
    }
    
    private var binding: FragmentRewardSelectorBinding by autoCleared()
    private val viewModel: RewardSelectorViewModel by viewModels()
    private lateinit var rewardItemAdapter: RewardItemAdapter
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_reward_selector,
            container,
            false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        rewardItemAdapter = RewardItemAdapter(this)
        
        arguments?.getString(EVENT_ID_ARG)?.let {
            viewModel.loadRewardsList(it)
        }
        
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        viewModel.rewardsList.observe(viewLifecycleOwner) {
            rewardItemAdapter.submitList(it)
        }
        
        binding.imageButtonBack.setOnClickListener {
            findNavController().popBackStack()
        }
        
        binding.recyclerViewRewards.apply {
            adapter = rewardItemAdapter
            layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
        }
    }
    
    override fun onRewardItemClicked(item: RewardItem) {
        findNavController().navigate(
            RewardSelectorFragmentDirections
                .actionRewardSelectorFragmentToAcknowledgementDialog(
                    imageUrlArg = getString(
                        R.string.qr_url_link,
                        getString(
                            R.string.qr_data_format,
                            item.activityId,
                            "60b762733a18d95422519836",
                            item._id
                        )
                    ),
                    titleTextArg = item.title,
                    subtitleTextArg = getString(R.string.description_show_qr_code),
                    buttonActionTextArg = getString(R.string.button_action_see_rewards)
                )
        )
    }
}