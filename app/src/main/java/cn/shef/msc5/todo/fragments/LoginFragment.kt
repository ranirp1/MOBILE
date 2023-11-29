package com.example.todo.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.todo.data.UserViewModel
import com.google.firebase.database.core.view.View

class LoginFragment: Fragment {
    class LoginFragment : Fragment() {
        companion object {
            const val LOGIN_SUCCESSFUL: String = "LOGIN_SUCCESSFUL"
        }

        private val userViewModel: UserViewModel by activityViewModels()
        private lateinit var savedStateHandle: SavedStateHandle

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            savedStateHandle = findNavController().previousBackStackEntry!!.savedStateHandle
            savedStateHandle.set(LOGIN_SUCCESSFUL, false)

            val usernameEditText = view.findViewById(R.id.username_edit_text)
            val passwordEditText = view.findViewById(R.id.password_edit_text)
            val loginButton = view.findViewById(R.id.login_button)

            loginButton.setOnClickListener {
                val username = usernameEditText.text.toString()
                val password = passwordEditText.text.toString()
                login(username, password)
            }
        }

        fun login(username: String, password: String) {
            userViewModel.login(username, password).observe(viewLifecycleOwner, Observer { result ->
                if (result.success) {
                    savedStateHandle.set(LOGIN_SUCCESSFUL, true)
                    findNavController().popBackStack()
                } else {
                    showErrorMessage()
                }
            })
        }

        fun showErrorMessage() {
            // Display a snackbar error message
        }
    }