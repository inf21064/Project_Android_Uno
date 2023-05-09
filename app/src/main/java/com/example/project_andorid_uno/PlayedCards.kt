package com.example.project_andorid_uno

import android.content.Context
import android.widget.Toast
import kotlin.random.Random
import kotlin.system.exitProcess

class PlayedCards(val startCard:ValueCard, val context: Context) {
    val playedCards: MutableList<PlayingCard> = mutableListOf(startCard)

    fun play(nextCard: PlayingCard) {
        val lastCard = playedCards.last()
        if(lastCard::class == nextCard::class) {
            when (nextCard) {
                is ValueCard -> {
                    if (lastCard is ValueCard){
                        if (lastCard.getCardColor == nextCard.getCardColor || lastCard.getCardValue == nextCard.getCardValue) {
                            UnoCards.deckPlayer.remove(nextCard)
                            playedCards.add(nextCard)
                        }
                    }else if (lastCard is FunctionCard){
                        lastCard as FunctionCard
                        if (lastCard.getCardColor == nextCard.getCardColor) {
                            UnoCards.deckPlayer.remove(nextCard)
                            playedCards.add(nextCard)

                        }
                    }else {
                        notAllowed(lastCard, nextCard)
                    }
                }
                is FunctionCard -> {

                    if(nextCard.getCardColor.equals("Any")) {
                        // == "Choose Color" || nextCard.getFunctionText == "Choose Color Draw Four"
                        if (nextCard.getFunctionText == "Choose Color") {
                            val tempCard = FunctionCard(CardColor.RED/*whatever the player chooses*/, "Choose Color"
                            )
                            UnoCards.deckPlayer.remove(nextCard)
                            playedCards.add(tempCard)
                        } else {
                            val tempCard = FunctionCard(CardColor.RED/*whatever the player chooses*/, "Choose Color Draw Four"
                            )
                            for (i in 1..4) {
                                UnoCards.deckEnemy.add(getRandomCard(UnoCards.deck))
                            }
                            UnoCards.deckPlayer.remove(nextCard)
                            playedCards.add(tempCard)
                        }
                    }
                    else if (lastCard is ValueCard){
                        if (lastCard.getCardColor == nextCard.getCardColor) {
                            checkForDrawTwo(nextCard)
                            UnoCards.deckPlayer.remove(nextCard)
                            playedCards.add(nextCard)
                        }
                    }else if (lastCard is FunctionCard){
                        if (lastCard.getCardColor == nextCard.getCardColor || lastCard.getFunctionText.equals(nextCard.getFunctionText) ) {
                            UnoCards.deckPlayer.remove(nextCard)
                            playedCards.add(nextCard)
                        }
                    }else {
                        notAllowed(lastCard, nextCard)
                    }
                }
                else -> {
                    println("Error with Cards: $lastCard and $nextCard")
                    exitProcess(0)
                }
            }
        }
    }

    private fun <T: PlayingCard>notAllowed(lastCard:T,nextCard: T) : Unit {
        val message = "On $lastCard does not fit a $nextCard"
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
    private fun getRandomCard(list: MutableList<PlayingCard>) : PlayingCard {
        val randomIndex = Random.nextInt(list.size);
        val randomElement = list[randomIndex]
        list.remove(randomElement)
        return randomElement
    }
    private fun checkForDrawTwo(card: FunctionCard){
        if(card.getFunctionText.equals("Draw Two"))
        {
            for (i in 1..2) {
                UnoCards.deckEnemy.add(getRandomCard(UnoCards.deck))
            }
        }
    }
}