package com.tinkerhub.replenish.features.itemdisplay

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tinkerhub.replenish.R
import com.tinkerhub.replenish.common.utils.autoCleared
import com.tinkerhub.replenish.data.interfaces.ItemDisplayItem
import com.tinkerhub.replenish.data.models.EventItem
import com.tinkerhub.replenish.databinding.FragmentItemDisplayBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class ItemDisplayFragment : Fragment() {
    
    companion object {
        const val ITEM_DISPLAY_ARG = "itemDisplayArg"
    }
    
    protected var binding: FragmentItemDisplayBinding by autoCleared()
    protected val viewModel: ItemDisplayViewModel by viewModels()
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_item_display,
            container,
            false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        
        arguments?.getParcelable<ItemDisplayItem>(ITEM_DISPLAY_ARG).let {
            if (it is EventItem) {
                viewModel.getEventDetails(it._id)
            } else {
                viewModel.itemDisplay.value = it
            }
        }
        
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        binding.imageButtonBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}