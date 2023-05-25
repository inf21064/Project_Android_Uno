package com.example.project_andorid_uno

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.project_andorid_uno.databinding.FragmentGameBinding
import com.example.project_andorid_uno.databinding.FragmentSettingsBinding

class gameFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentGameBinding>(inflater,
            R.layout.fragment_game,container,false)
        binding.helperPlayButton.setOnClickListener {

        }

        return binding.root
    }
}