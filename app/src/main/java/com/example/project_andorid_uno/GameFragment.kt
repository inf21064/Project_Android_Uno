package com.example.project_andorid_uno

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project_andorid_uno.databinding.FragmentGameBinding
import kotlin.random.Random
import kotlin.system.exitProcess

class GameFragment : Fragment() {
    lateinit var imageView: ImageView
    lateinit var recyclerView: RecyclerView

    private lateinit var drawButton: Button
    private lateinit var unoButton: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentGameBinding>(inflater,
            R.layout.fragment_game,container,false)
        drawButton = binding.drawCardButton
        unoButton = binding.sayUnoButton
        imageView = binding.playedUnoCardView
        recyclerView = binding.rv

        /*binding.drawCardButton.setOnClickListener {
                if(UnoCards.playDeck.isEmpty()){
                    val message = "No more Cards, Game Over!" // make string later for different languages
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                }else{
                    val tempCard = getRandomCard(UnoCards.playDeck, it)
                    UnoCards.deckPlayer.add(tempCard)
                    recyclerView.adapter?.notifyItemInserted(UnoCards.deckPlayer.size-1)
                }
            }

        binding.sayUnoButton.setOnClickListener {
            val message = "UNO!"
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }*/

        binding.stopGameButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_gameFragment_to_resultFragment)
        }
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val startingCard = getRandomCard(UnoCards.playDeck)
        updateImage(startingCard.imageResId)
        val playedCards = PlayedCards(startingCard, context, imageView)
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = RecyclerViewAdapter(this.context, {
                selectedItem -> setImageResource(selectedItem) },
            IntRange(0, UnoCards.deckPlayer.size-1).toList(), playedCards) //hier wird die karte in der mitte gesetzt

        setDrawCardButtonListener(playedCards)
        setUnoButtonListener(playedCards)
    }
    private fun setImageResource(selectedItem: PlayingCard) {
        imageView.setImageResource(selectedItem.imageResId)
    }

    fun updateImage(imageResId: Int) {
        imageView.setImageResource(imageResId)
    }

    private fun setDrawCardButtonListener(playedCards: PlayedCards) {
        drawButton.setOnClickListener {
            if (UnoCards.playDeck.isEmpty()) {
                val message = "No more Cards, Game Over!" // make string later for different languages
                Toast.makeText(context, message, Toast.LENGTH_LONG).show()
            } else if (playedCards.cardDrawn == false) {
                val tempCard = getRandomCard(UnoCards.playDeck)
                UnoCards.deckPlayer.add(tempCard)
                recyclerView.adapter = RecyclerViewAdapter(this.context, {
                        selectedItem -> setImageResource(selectedItem) },
                    IntRange(0, UnoCards.deckPlayer.size-1).toList(), playedCards)
                recyclerView.smoothScrollToPosition(UnoCards.deckPlayer.size - 1)
                playedCards.cardDrawn = true
            }else{
                val message = "You already drew a card!" // make string later for different languages
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setUnoButtonListener(playedCards: PlayedCards) {
        unoButton.setOnClickListener {
            val message = "UNO!"
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

}