package com.tinkerhub.replenish.common.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.tinkerhub.replenish.common.utils.autoCleared
import com.tinkerhub.replenish.databinding.DialogFragmentPointsReceivedBinding

class PointsReceivedDialog : DialogFragment() {
    
    companion object {
        const val POINTS_INT_ARG = "pointsIntArg"
    }
    
    private var binding: DialogFragmentPointsReceivedBinding by autoCleared()
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogFragmentPointsReceivedBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        binding.pointsReceived = arguments?.getInt(POINTS_INT_ARG)
        
        binding.buttonAction.setOnClickListener {
            dismiss()
        }
        
        binding.buttonClose.setOnClickListener {
            dismiss()
        }
    }
}