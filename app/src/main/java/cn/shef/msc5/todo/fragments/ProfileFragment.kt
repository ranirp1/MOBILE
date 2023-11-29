package com.example.todo.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.R
import androidx.navigation.fragment.findNavController
import com.example.todo.data.UserViewModel

class ProfileFragment: Fragment () {
        private val userViewModel: UserViewModel by activityViewModels()

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            val navController = findNavController()
            val currentBackStackEntry = navController.currentBackStackEntry!!
            val savedStateHandle = currentBackStackEntry.savedStateHandle
            savedStateHandle.getLiveData<Boolean>(LoginFragment.LOGIN_SUCCESSFUL)
                .observe(currentBackStackEntry, Observer { success ->
                    if (!success) {
                        val startDestination = navController.graph.startDestination
                        val navOptions = NavOptions.Builder()
                            .setPopUpTo(startDestination, true)
                            .build()
                        navController.navigate(startDestination, null, navOptions)
                    }
                })
        }

    ...
}
            userViewModel.user.observe(viewLifecycleOwner, Observer { user ->
                if (user != null) {
                    showWelcomeMessage()
                } else {
                    navController.navigate(R.id.login_fragment)
                }
            })
        }

        private fun showWelcomeMessage() {
            ...
        }
    }
}