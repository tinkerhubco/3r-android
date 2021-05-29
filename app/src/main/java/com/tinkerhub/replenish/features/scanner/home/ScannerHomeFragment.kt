package com.tinkerhub.replenish.features.scanner.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.tinkerhub.replenish.common.utils.autoCleared
import com.tinkerhub.replenish.databinding.FragmentScannerHomeBinding

class ScannerHomeFragment : Fragment() {
    
    private var binding: FragmentScannerHomeBinding by autoCleared()
    private val viewModel: ScannerHomeViewModel by viewModels()
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScannerHomeBinding.inflate(inflater, container, false)
        
        return binding.root
    }
}