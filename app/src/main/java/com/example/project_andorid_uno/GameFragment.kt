package com.example.project_andorid_uno

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project_andorid_uno.databinding.FragmentGameBinding
import kotlin.system.exitProcess

class GameFragment : Fragment() {
    lateinit var imageView: ImageView
    lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentGameBinding>(inflater,
            R.layout.fragment_game,container,false)
        imageView = binding.playedUnoCardView
        recyclerView = binding.rv
        binding.stopGameButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_gameFragment_to_resultFragment)
        }

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = RecyclerViewAdapter({selectedItem -> imageView.setImageResource(selectedItem.imageResId)},
            IntRange(0, 15).toList())
    }

}