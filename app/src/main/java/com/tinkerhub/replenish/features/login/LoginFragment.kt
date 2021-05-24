package com.tinkerhub.replenish.features.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tinkerhub.replenish.R
import com.tinkerhub.replenish.common.utils.autoCleared
import com.tinkerhub.replenish.databinding.FragmentLoginBinding
import com.tinkerhub.replenish.features.register.registrationinput.RegistrationInputSharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    
    companion object {
        const val NAME_INPUT_KEY = "NameInputKey"
        const val EMAIL_INPUT_KEY = "EmailInputKey"
        const val USERNAME_INPUT_KEY = "UsernameInputKey"
        const val PASSWORD_INPUT_KEY = "PasswordInputKey"
    }
    
    private var binding: FragmentLoginBinding by autoCleared()
    private val viewModel: LoginViewModel by viewModels()
    private val registrationInputSharedViewModel:
        RegistrationInputSharedViewModel by activityViewModels()
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_login,
            container,
            false
        )
        binding.viewModel = viewModel
        
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        binding.buttonSignUp.setOnClickListener {
            findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToRegistrationInputFragment(
                    NAME_INPUT_KEY
                )
            )
        }
        
        setupRegistrationInputs()
    }
    
    private fun setupRegistrationInputs() {
        registrationInputSharedViewModel.registrationInputs.let {
            it[NAME_INPUT_KEY] = RegistrationInputSharedViewModel.Input(
                position = 0,
                hint = getString(R.string.name_input_label),
                buttonText = getString(R.string.next)
            )
            
            it[EMAIL_INPUT_KEY] = RegistrationInputSharedViewModel.Input(
                position = 1,
                hint = getString(R.string.email_input_label),
                buttonText = getString(R.string.next)
            )
            
            it[USERNAME_INPUT_KEY] = RegistrationInputSharedViewModel.Input(
                position = 2,
                hint = getString(R.string.username_input_label),
                buttonText = getString(R.string.next)
            )
            
            it[PASSWORD_INPUT_KEY] = RegistrationInputSharedViewModel.Input(
                position = 3,
                hint = getString(R.string.password_input_label),
                buttonText = getString(R.string.sign_up_button),
                action = {
                    findNavController().popBackStack(R.id.loginFragment, false)
                }
            )
        }
    }
}