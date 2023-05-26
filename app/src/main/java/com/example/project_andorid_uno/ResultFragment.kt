package com.example.project_andorid_uno

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.project_andorid_uno.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentResultBinding>(inflater,
            R.layout.fragment_result,container,false)
        binding.playAgainButton.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_resultFragment_to_gameFragment)
        }
        binding.returnHomeButton.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_resultFragment_to_homeFragment)
        }
        return inflater.inflate(R.layout.fragment_result, container, false)
    }
}