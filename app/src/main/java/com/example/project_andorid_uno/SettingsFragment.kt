package com.example.project_andorid_uno

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.project_andorid_uno.databinding.FragmentSettingsBinding

var numOfCards = 7

class settingsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentSettingsBinding>(inflater,
            R.layout.fragment_settings,container,false)
        binding.numOfCards = numOfCards

        binding.applySettingsBtn.setOnClickListener {
            numOfCards = binding.numOfCardsTxtInput.text.toString().toInt()
            if (numOfCards < 1 || numOfCards > 15) {
                binding.numOfCardsTxtInput.error = "Please enter a number between 1 and 15"
                return@setOnClickListener
            } else {
                it.findNavController().navigate(R.id.action_settingsFragment_to_homeFragment)
            }
        }

        return binding.root
    }

    fun getNumOfCards() : Int{
        return numOfCards
    }
}