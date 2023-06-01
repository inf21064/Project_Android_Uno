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
        chooseColorTextView = binding.chooseColorTextView!!
        chooseColorRadioButtonGroup = binding.chooseColorRadioButtonGroup!!
        changeChooseColorVisibility(false)

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

        val startingCard = getRandomValueCard(UnoCards.playDeck)
        updateImage(startingCard.imageResId)
        val playedCards = PlayedCards(startingCard, context, imageView, this)
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = RecyclerViewAdapter(this.context,
            IntRange(0, UnoCards.deckPlayer.size-1).toList(), playedCards) //hier wird die karte in der mitte gesetzt
        endTurnButton.setOnClickListener {
            playedCards.enemyPlay()
        }
        /*chooseColorRadioButtonGroup.setOnCheckedChangeListener { group, checkedRadioButtonId ->
            val radioButton = group.findViewById<RadioButton>(checkedRadioButtonId)
            var cardColor : CardColor = CardColor.RED
            when(radioButton.text.toString()) {
                R.string.redCheckBox.toString() -> cardColor = CardColor.RED
                R.string.yellowCheckBox.toString() -> cardColor = CardColor.YELLOW
                R.string.greenCheckBox.toString() -> cardColor = CardColor.GREEN
                R.string.blueCheckBox.toString() -> cardColor = CardColor.BLUE
            }
            playedCards.setCardColor(cardColor)
            changeChooseColorVisibility(false)
        }*/
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
    fun changeChooseColorVisibility(setParameter: Boolean){
        if (setParameter) {
            chooseColorTextView.visibility = View.VISIBLE
            chooseColorRadioButtonGroup.visibility = View.VISIBLE
        } else {
            chooseColorTextView.visibility = View.INVISIBLE
            chooseColorRadioButtonGroup.visibility = View.INVISIBLE
        }
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
        }
    }

    override fun onOptionSelected(color: CardColor) {
        //Handle the selected option
        Toast.makeText(context, color.toString(), Toast.LENGTH_LONG)
    }

}