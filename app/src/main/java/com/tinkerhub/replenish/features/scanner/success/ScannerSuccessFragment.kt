package com.tinkerhub.replenish.features.scanner.success

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.tinkerhub.replenish.common.utils.autoCleared
import com.tinkerhub.replenish.databinding.FragmentScannerSuccessBinding

class ScannerSuccessFragment : Fragment() {
    
    private var binding: FragmentScannerSuccessBinding by autoCleared()
    private val viewModel: ScannerSuccessViewModel by viewModels()
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScannerSuccessBinding.inflate(inflater, container, false)
        
        return binding.root
    }
}