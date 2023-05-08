package com.example.project_andorid_uno

import android.content.Context
import android.widget.Toast
import kotlin.system.exitProcess

class PlayedCards(val startCard:ValueCard, val context: Context) {
    val playedCards: MutableList<PlayingCard> = mutableListOf(startCard)

    fun play(nextCard: PlayingCard) {
        val lastCard = playedCards.last()
        if(lastCard::class == nextCard::class) {
            when (nextCard) {
                is ValueCard -> {
                    lastCard as ValueCard
                    if(lastCard.getCardColor == nextCard.getCardColor && lastCard.getCardValue == nextCard.getCardValue) {
                        playedCards.add(nextCard)
                        println("$lastCard equals $nextCard")
                    } else if (lastCard.getCardColor == nextCard.getCardColor || lastCard.getCardValue == nextCard.getCardValue) {
                        playedCards.add(nextCard)
                        println("On $lastCard fits a $nextCard")
                    } else {
                        notAllowed(lastCard, nextCard)
                    }
                }
                is FunctionCard -> {
                    lastCard as FunctionCard
                    if (lastCard.getCardColor == nextCard.getCardColor && lastCard.getFunctionText == nextCard.getFunctionText) {
                        playedCards.add(nextCard)
                        println("$lastCard equals $nextCard")
                    } else if (lastCard.getCardColor == nextCard.getCardColor || lastCard.getFunctionText.equals(nextCard.getFunctionText) ) {
                        playedCards.add(nextCard)
                        println("On $lastCard fits a $nextCard")
                    } else {
                        notAllowed(lastCard, nextCard)
                    }
                }
                else -> {
                    println("Error with Cards: $lastCard and $nextCard")
                    exitProcess(0)
                }
            }
        } else if (nextCard is ValueCard && lastCard is FunctionCard || nextCard is FunctionCard && lastCard is ValueCard) {
            if (lastCard.getCardColor == nextCard.getCardColor) {
                playedCards.add(nextCard)
                println("On $lastCard fits a $nextCard")
            }
            else {
                notAllowed(lastCard,nextCard)
            }
        } else {
            println("Error with Cards: $lastCard and $nextCard")
            exitProcess(0)
        }
    }

    private fun <T: PlayingCard>notAllowed(lastCard:T,nextCard: T) : Unit {
        val message = "On $lastCard does not fit a $nextCard"
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }


    private fun getStartCard() : PlayingCard {
        val random = kotlin.random.Random
        return when (random.nextInt(4)) {
            0 -> ValueCard(CardColor.RED, random.nextInt(1,10))
            1 -> ValueCard(CardColor.YELLOW, random.nextInt(1,10))
            2 -> ValueCard(CardColor.GREEN, random.nextInt(1,10))
            3 -> ValueCard(CardColor.BLUE, random.nextInt(1,10))
            else -> {ValueCard(CardColor.ANY, -1000)}
        }
    }
}