package com.example.project_andorid_uno

import android.content.Context
import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project_andorid_uno.databinding.FragmentGameBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random
import kotlin.system.exitProcess

class GameFragment : Fragment(), ChooseColorDialogFragment.OnOptionSelectedListener {
    private lateinit var imageView: ImageView
    private lateinit var recyclerView: RecyclerView
    private lateinit var drawButton: Button
    private lateinit var unoButton: Button
    private lateinit var endTurnButton: Button
    private lateinit var chooseColorTextView: TextView
    private lateinit var chooseColorRadioButtonGroup: RadioGroup

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentGameBinding>(inflater,
            R.layout.fragment_game,container,false)
        drawButton = binding.drawCardButton
        endTurnButton = binding.endTurnButton
        endTurnButton.visibility = View.INVISIBLE
        unoButton = binding.sayUnoButton
        imageView = binding.playedUnoCardView
        recyclerView = binding.rv

        binding.stopGameButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_gameFragment_to_resultFragment)  // player ends game with reset button
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val startingCard = getRandomValueCard(UnoCards.playDeck)
        updateImage(startingCard.imageResId)
        val playedCards = PlayedCards(startingCard, context, imageView, this)
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = RecyclerViewAdapter(this.context,
            IntRange(0, UnoCards.deckPlayer.size-1).toList(), playedCards)
        endTurnButton(playedCards)
        setDrawCardButtonListener(playedCards)
        setUnoButtonListener(playedCards)
    }
    fun launchChooseColorDialogFragment() {
        //why does it do the following code async?
        val chooseColorDialogFragment = ChooseColorDialogFragment()
        chooseColorDialogFragment.setOnOptionSelectedListener(this)
        chooseColorDialogFragment.show(childFragmentManager, "ChooseColorDialogFragment")
        childFragmentManager.executePendingTransactions()
    }
    fun changeEndTurnButtonVisibility(setParameter: Boolean){

        if (setParameter) {
            endTurnButton.visibility = View.VISIBLE
            drawButton.visibility = View.INVISIBLE
        } else {
            endTurnButton.visibility = View.INVISIBLE
            drawButton.visibility = View.VISIBLE
        }
    }

    fun updateImage(imageResId: Int) {
        imageView.setImageResource(imageResId)
    }

    private fun endTurnButton(playedCards: PlayedCards) {
        endTurnButton.setOnClickListener {
            val coroutineScope = CoroutineScope(Dispatchers.Main)
        playedCards.skipTurns = 0
        playedCards.enemyPlay()
        coroutineScope.launch {
            delay(1000)
            playedCards.updateImage(playedCards.playedCards.last().imageResId)
            if(UnoCards.deckEnemy.isEmpty() || UnoCards.playDeck.isEmpty()){
                delay(2000)
                it.findNavController().navigate(R.id.action_gameFragment_to_resultFragment) //  player hits endturn button and enemy wins in his turn --> kein craash
            }
        }
            coroutineScope.launch {
                delay(1000)
                while(playedCards.playedSkipReverse == true)
                {

                    playedCards.enemyPlay()
                    delay(1000)
                    playedCards.updateImage(playedCards.playedCards.last().imageResId)
                    if(UnoCards.deckEnemy.isEmpty() || UnoCards.playDeck.isEmpty()){
                        delay(2000)
                        it.findNavController().navigate(R.id.action_gameFragment_to_resultFragment) // player hits endturn utton and enemy wins after a skip card
                    }
                }
            }
            recyclerView.adapter = RecyclerViewAdapter(this.context,
                IntRange(0, UnoCards.deckPlayer.size - 1).toList(), playedCards
            )
        }
    }
    private fun setDrawCardButtonListener(playedCards: PlayedCards) {
        drawButton.setOnClickListener {
            if (UnoCards.playDeck.isEmpty()) {
                val message = "No more Cards, Game Over!" // make string later for different languages
                Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                it.findNavController().navigate(R.id.action_gameFragment_to_resultFragment) // player draws all cards and game ends itself
            } else if (playedCards.cardDrawn == false) {
                val tempCard = getRandomCard(UnoCards.playDeck)
                UnoCards.deckPlayer.add(tempCard)
                recyclerView.adapter = RecyclerViewAdapter(this.context,
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
            playedCards.saidUno = true
        }
    }

    override fun onOptionSelected(color: CardColor) {
        //Handle the selected option
        Toast.makeText(context, color.toString(), Toast.LENGTH_LONG)
    }

}