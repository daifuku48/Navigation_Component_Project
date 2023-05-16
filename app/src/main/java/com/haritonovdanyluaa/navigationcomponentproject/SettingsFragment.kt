package com.haritonovdanyluaa.navigationcomponentproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.haritonovdanyluaa.navigationcomponentproject.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    private var _binding : FragmentSettingsBinding? = null
    private val binding get() = _binding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = resources.getStringArray(R.array.count_of_boxes)
        val arrayAdapter = ArrayAdapter(requireContext(), androidx.transition.R.layout.support_simple_spinner_dropdown_item, list)
        binding?.spinner?.adapter = arrayAdapter


        binding?.cancelButton?.setOnClickListener {
            findNavController().navigateUp()
        }

        binding?.confirmButton?.setOnClickListener {
            val options = configureOptions()
            findNavController().navigateUp()
        }
    }

    private fun configureOptions(): Options {
        val count = when (binding?.spinner?.selectedItem.toString()) {
            "1 Box" -> 1
            "2 Boxes" -> 2
            "3 Boxes" -> 3
            "4 Boxes" -> 4
            "5 Boxes" -> 5
            else -> {3}
        }
        val timerEnabled = binding?.timerCheckBox?.isChecked
        return Options(count, timerEnabled!!)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}