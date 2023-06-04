package com.example.project_andorid_uno

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import kotlin.system.exitProcess
import android.content.Context
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class RecyclerViewAdapter (val context: Context?,
                           var data: List<Int>, val playedCards: PlayedCards) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>()
{
    val coroutineScope = CoroutineScope(Dispatchers.Main)
    lateinit var tempView: View

    inner class MyViewHolder(val row: View) : RecyclerView.ViewHolder(row) {
        val cardView = row.findViewById<ImageView>(R.id.cardView)
        fun bind(data: PlayingCard) {
            row.setOnClickListener{
                playedCards.playerPlay(data)
                tempView = it
                if (playedCards.playedCards.last().color == CardColor.ANY) {
                    var temp = playedCards.playedCards
                    val bundle = bundleOf("playedCards" to temp)

                    it.findNavController().navigate(R.id.action_gameFragment_to_chooseColorFragment, bundle)
                } else {
                    playedCards.updateImage(playedCards.playedCards.last().imageResId)
                    checkAndPlayEnemy()
                }

            }
        }
    }
    fun checkAndPlayEnemy() {
        if(UnoCards.deckPlayer.isEmpty() || UnoCards.playDeck.isEmpty()){
            // player wins with last card
            validateNavigationToResultFragment()
        }else if(playedCards.whoHasTurn == "Enemy"){
            playedCards.enemyPlay()
            if(UnoCards.deckEnemy.isEmpty() || UnoCards.playDeck.isEmpty()){
                coroutineScope.launch {
                    playedCards.playedSkipReverse = false
                    delay(2000)
                    // player plays and enemy wins in his following turn
                    validateNavigationToResultFragment()
                }
            }
            coroutineScope.launch {
                delay(1000)
                playedCards.updateImage(playedCards.playedCards.last().imageResId)
            }
            coroutineScope.launch {
                delay(1000)
                while(playedCards.playedSkipReverse)
                {
                    playedCards.enemyPlay()
                    delay(1000)
                    playedCards.updateImage(playedCards.playedCards.last().imageResId)
                    if(UnoCards.deckEnemy.isEmpty() || UnoCards.playDeck.isEmpty()){
                        playedCards.playedSkipReverse = false
                        delay(2000)
                        // enemy played skip turn card and then wins
                        validateNavigationToResultFragment()
                    }
                }
            }
            playedCards.whoHasTurn = "Player"
            updatePositionData()
            notifyDataSetChanged()
        }
    }
    private fun updatePositionData() {
        data = IntRange(0, UnoCards.deckPlayer.size-1).toList()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)
        return MyViewHolder(layout)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.cardView.setImageResource(getUnoCard(position).getterImageResId())
        holder.bind(UnoCards.deckPlayer[position])
    }
    override fun getItemCount(): Int = data.size

    private fun getUnoCard(cardId : Int) : PlayingCard {
        if (cardId in 0 until UnoCards.deckPlayer.size) {
            return UnoCards.deckPlayer[cardId]
        } else  {
                println("Cant select image")
                exitProcess(0)
        }
    }
    private fun validateNavigationToResultFragment() {
        try {
            tempView.findNavController().navigate(R.id.action_gameFragment_to_resultFragment)
        } catch (e: IllegalArgumentException) {
            println("Exception: $e")
        }
    }
}
fun getRandomValueCard(list: MutableList<PlayingCard>) : PlayingCard {
    while(true){
        val randomIndex = Random.nextInt(list.size);
        val randomElement = list[randomIndex]
        if(randomElement is ValueCard){
            list.remove(randomElement)
            return randomElement
        }
    }
}
fun getRandomCard(list: MutableList<PlayingCard>) : PlayingCard {
    val randomIndex = Random.nextInt(list.size);
    val randomElement = list[randomIndex]
    list.remove(randomElement)
    return randomElement
}

