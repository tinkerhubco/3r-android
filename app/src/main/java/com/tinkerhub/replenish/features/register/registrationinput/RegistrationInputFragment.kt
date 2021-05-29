package com.tinkerhub.replenish.features.register.registrationinput

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tinkerhub.replenish.common.utils.autoCleared
import com.tinkerhub.replenish.databinding.FragmentRegistrationInputBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationInputFragment : Fragment() {
    
    companion object {
        const val INPUT_KEY_ARG = "inputKeyArg"
    }
    
    private var binding: FragmentRegistrationInputBinding by autoCleared()
    private val viewModel: RegistrationInputViewModel by viewModels()
    private val sharedViewModel: RegistrationInputSharedViewModel by activityViewModels()
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrationInputBinding.inflate(inflater, container, false)
        viewModel.inputKey = arguments?.getString(INPUT_KEY_ARG) ?: ""
        
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        binding.imageButtonBack.setOnClickListener {
            findNavController().popBackStack()
        }
        
        sharedViewModel.registrationInputs.let { inputs ->
            inputs[viewModel.inputKey]?.let { currentInput ->
                binding.textInputLayoutItem.hint = currentInput.hint
                
                binding.buttonAction.text = currentInput.buttonText
                
                binding.editTextItem.doOnTextChanged { text, _, _, _ ->
                    currentInput.value = text.toString()
                }
                
                binding.buttonAction.setOnClickListener {
                    val nextItem = inputs.filter {
                        it.value.position == currentInput.position + 1
                    }
                    
                    if (nextItem.isNotEmpty()) {
                        findNavController().navigate(
                            RegistrationInputFragmentDirections
                                .actionGoToNextRegistrationInputFragment(
                                    nextItem.keys.first()
                                )
                        )
                    } else {
                        currentInput.action()
                    }
                }
            }
        }
    }
}