package com.haritonovdanyluaa.navigationcomponentproject

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.haritonovdanyluaa.navigationcomponentproject.databinding.FragmentMenuBoxBinding

class MenuFragment : Fragment() {

    private var _binding : FragmentMenuBoxBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuBoxBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val countOfBoxes = findNavController().currentBackStackEntry?.savedStateHandle?.get<Int>("COUNT_BOXES")
        val timerEnabled = findNavController().currentBackStackEntry?.savedStateHandle?.get<Boolean>("TIMER_ENABLED")

        binding?.openBoxButton?.setOnClickListener {

            val direction = MenuFragmentDirections.actionMenuFragmentToBoxSelectionFragment()
                        .setCountOfBoxes(countOfBoxes ?: 3)
                        .setEnableTimer(timerEnabled == true)
            findNavController().navigate(direction)
        }

        binding?.settingButton?.setOnClickListener {
            val direction = MenuFragmentDirections.actionMenuFragmentToSettingsFragment()
            findNavController().navigate(direction)
        }

        binding?.aboutBox?.setOnClickListener {
            val direction = MenuFragmentDirections.actionMenuFragmentToAboutFragment()
            findNavController().navigate(direction)
        }
        binding?.exitButton?.setOnClickListener {
            requireActivity().finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}