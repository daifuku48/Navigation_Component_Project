package com.haritonovdanyluaa.navigationcomponentproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.haritonovdanyluaa.navigationcomponentproject.databinding.BoxItemBinding
import com.haritonovdanyluaa.navigationcomponentproject.databinding.FragmentAboutBinding
import com.haritonovdanyluaa.navigationcomponentproject.databinding.FragmentBoxSelectionBinding

class BoxSelectionFragment : Fragment() {

    private var _binding : FragmentBoxSelectionBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBoxSelectionBinding.inflate(inflater, container, false)
        return binding?.root



      //  binding?.flow?.referencedIds
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createBoxes()
    }

    private fun createBoxes()
    {
        val boxBindings = (0 until 10).map { index ->
            val boxBinding = BoxItemBinding.inflate(layoutInflater)
            boxBinding.root.id = View.generateViewId()
            boxBinding.boxTitleTextView.text = getString(R.string.box_title, index + 1)
            //boxBinding.root.setOnClickListener { view -> onBoxSelected(view) }
            boxBinding.root.tag = index
            binding?.root?.addView(boxBinding.root)
            boxBinding
        }
        binding?.flow?.referencedIds = boxBindings.map { it.root.id }.toIntArray()
    }

    companion object{
        const val CODE_COUNT_BOXES = "CODE_COUNT_BOXES"
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}