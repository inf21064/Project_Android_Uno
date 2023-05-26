package com.example.project_andorid_uno

import android.icu.number.IntegerWidth
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.project_andorid_uno.databinding.FragmentResultBinding

class ResultFragment : Fragment() {
    lateinit var result: ResultData
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /*this is for testing*/
        result = ResultData("1","2", "3")

        val binding = DataBindingUtil.inflate<FragmentResultBinding>(inflater,
            R.layout.fragment_result,container,false)
        binding.resultData = result

        binding.playAgainButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_resultFragment_to_gameFragment)
        }
        binding.returnHomeButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_resultFragment_to_homeFragment)
        }
        return binding.root
    }
}