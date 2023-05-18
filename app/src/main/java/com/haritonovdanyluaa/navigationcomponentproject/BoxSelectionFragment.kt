package com.haritonovdanyluaa.navigationcomponentproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.haritonovdanyluaa.navigationcomponentproject.databinding.BoxItemBinding
import com.haritonovdanyluaa.navigationcomponentproject.databinding.FragmentAboutBinding
import com.haritonovdanyluaa.navigationcomponentproject.databinding.FragmentBoxSelectionBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.properties.Delegates
import kotlin.random.Random

class BoxSelectionFragment : Fragment() {

    private var _binding : FragmentBoxSelectionBinding? = null
    private val binding get() = _binding

    private var boxIndex by Delegates.notNull<Int>()
    private var boxCount by Delegates.notNull<Int>()
    private var enabledTimer by Delegates.notNull<Boolean>()
    private val args by navArgs<BoxSelectionFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBoxSelectionBinding.inflate(inflater, container, false)
        boxCount = args.countOfBoxes
        boxIndex = Random.nextInt(1, boxCount)
        enabledTimer = args.enableTimer
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createBoxes()
        if (enabledTimer)
        {
            setTimer()
        }
    }

    private fun setTimer()
    {
        lifecycleScope.launch {
            var count = 10
            while(count != 0)
            {
                binding?.timerTextView?.text = "00:$count"
                delay(1000)
                count--
            }
            binding?.timerTextView?.text = getString(R.string.you_ve_lost)
        }
    }


    private fun createBoxes()
    {
        val boxBindings = (0 until boxCount).map { index ->
            val boxBinding = BoxItemBinding.inflate(layoutInflater)
            boxBinding.root.id = View.generateViewId()
            boxBinding.boxTitleTextView.text = getString(R.string.box_title, index + 1)
            boxBinding.root.setOnClickListener { view -> onBoxSelected(view) }
            boxBinding.root.tag = index
            binding?.root?.addView(boxBinding.root)
            boxBinding
        }
        binding?.flow?.referencedIds = boxBindings.map { it.root.id }.toIntArray()
    }

    private fun onBoxSelected(view: View)
    {
        if (view.tag as Int == boxIndex) {
            val direction = BoxSelectionFragmentDirections.actionBoxSelectionFragmentToCongratulationsFragment()
            findNavController().navigate(direction)
        } else {
            Toast.makeText(context, R.string.box_is_empty, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}