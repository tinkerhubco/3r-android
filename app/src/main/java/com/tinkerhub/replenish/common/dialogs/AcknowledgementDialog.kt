package com.tinkerhub.replenish.common.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import com.tinkerhub.replenish.common.ui.setImageUrl
import com.tinkerhub.replenish.common.utils.autoCleared
import com.tinkerhub.replenish.databinding.DialogFragmentAcknowledgmentBinding

class AcknowledgementDialog : DialogFragment() {
    
    companion object {
        const val IMAGE_URL_ARG = "imageUrlArg"
        const val TITLE_TEXT_ARG = "titleTextArg"
        const val SUBTITLE_TEXT_ARG = "subtitleTextArg"
        const val BUTTON_ACTION_TEXT_ARG = "buttonActionTextArg"
    }
    
    private var binding: DialogFragmentAcknowledgmentBinding by autoCleared()
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogFragmentAcknowledgmentBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setImageUrl(binding.imageViewDisplay, arguments?.getString(IMAGE_URL_ARG))
        binding.textviewTitle.text = arguments?.getString(TITLE_TEXT_ARG)
        binding.textviewSubtitle.text = arguments?.getString(SUBTITLE_TEXT_ARG)
        arguments?.getString(BUTTON_ACTION_TEXT_ARG).let {
            binding.buttonAction.text = it
            binding.buttonAction.isVisible = !it.isNullOrEmpty()
        }
        
        binding.buttonAction.setOnClickListener {
            dismiss()
        }
        
        binding.buttonClose.setOnClickListener {
            dismiss()
        }
    }
}