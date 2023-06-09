package com.haritonovdanyluaa.navigationcomponentproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.haritonovdanyluaa.navigationcomponentproject.databinding.FragmentAboutBinding
import com.haritonovdanyluaa.navigationcomponentproject.databinding.FragmentCongratulationsBinding

class CongratulationsFragment : Fragment() {
    private var _binding : FragmentCongratulationsBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCongratulationsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.goBackButton?.setOnClickListener {
            findNavController().popBackStack(R.id.menuFragment, false)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}