package com.example.project_andorid_uno

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.project_andorid_uno.databinding.FragmentHomeBinding
import com.example.project_andorid_uno.databinding.FragmentSettingsBinding
import java.io.Console

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
            println(numOfCards)
        }

        return binding.root
    }
}