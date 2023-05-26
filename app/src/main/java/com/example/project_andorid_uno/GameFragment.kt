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
    private fun getUnoCard(cardId : Int) : Int {
        return when (cardId) {
            0 -> R.drawable.green_0_card_clipart_md
            1 -> R.drawable.green_1_card_clipart_md
            2 -> R.drawable.green_2_card_clipart_md
            3 -> R.drawable.green_reverse_card_clipart_md
            4 -> R.drawable.blue_reverse_card_clipart_md
            5 -> R.drawable.blue_5_card_clipart_md
            6 -> R.drawable.blue_skip_card_clipart_md
            7 -> R.drawable.blue_4_card_clipart_md
            8 -> R.drawable.red_0_card_clipart_md
            9 -> R.drawable.red_2_card_clipart_md
            10 -> R.drawable.green_5_card_clipart_md
            11 -> R.drawable.wild_card_clipart_md
            12 -> R.drawable.red_4_card_clipart_md
            13 -> R.drawable.green_3_card_clipart_md
            14 -> R.drawable.blue_9_card_clipart_md
            15 -> R.drawable.yellow_4_card_clipart_md
            else -> {
                println("Cant select image")
                exitProcess(0)
            }
        }
    }
}