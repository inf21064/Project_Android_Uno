package com.example.project_andorid_uno

import android.content.Context
import android.widget.Toast
import kotlin.random.Random
import kotlin.system.exitProcess

class PlayedCards(val startCard:ValueCard, val context: Context) {
    val playedCards: MutableList<PlayingCard> = mutableListOf(startCard)

    fun playerPlay(nextCard: PlayingCard) {
        val lastCard = playedCards.last()
        if(lastCard::class == nextCard::class) {
            when (nextCard) {
                is ValueCard -> {
                    lastCard as ValueCard
                    if (lastCard.getCardColor == nextCard.getCardColor || lastCard.getCardValue == nextCard.getCardValue) {
                        UnoCards.deckPlayer.remove(nextCard)
                        playedCards.add(nextCard)
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
                                val tempCard2 = getRandomCard(UnoCards.deck)
                                UnoCards.deckEnemy.add(tempCard2)
                                UnoCards.deck.remove(tempCard2)
                            }
                            UnoCards.deckPlayer.remove(nextCard)
                            playedCards.add(tempCard)
                        }
                    }else if (lastCard is FunctionCard){
                        if (lastCard.getCardColor == nextCard.getCardColor || lastCard.getFunctionText.equals(nextCard.getFunctionText) ) {
                            checkForDrawTwoPlayer(nextCard)
                            UnoCards.deckPlayer.remove(nextCard)
                            playedCards.add(nextCard)
                        }else {
                            notAllowed(lastCard, nextCard)
                        }
                    }
                }else -> {
                    println("Error with Cards: $lastCard and $nextCard")
                    exitProcess(0)
                }
            }
        }
        else if (lastCard::class != nextCard::class){
            when (nextCard) {
                is ValueCard  -> {
                    lastCard as FunctionCard
                    if (lastCard.getCardColor == nextCard.getCardColor) {
                        UnoCards.deckPlayer.remove(nextCard)
                        playedCards.add(nextCard)
                    }else {
                        notAllowed(lastCard, nextCard)
                    }
                }
                is FunctionCard -> {
                    lastCard as ValueCard
                        if (lastCard.getCardColor == nextCard.getCardColor) {
                            checkForDrawTwoPlayer(nextCard)
                            UnoCards.deckPlayer.remove(nextCard)
                            playedCards.add(nextCard)
                        }else {
                            notAllowed(lastCard, nextCard)
                        }
                }
            }
        }
    }

    private fun <T: PlayingCard>notAllowed(lastCard: T,nextCard: T) : Unit {
        val message = "On ${lastCard.toString()} does not fit a ${nextCard.toString()}"
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun sayUno() : Unit {
        val message = "UNO!"
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
    private fun getRandomCard(list: MutableList<PlayingCard>) : PlayingCard {
        val randomIndex = Random.nextInt(list.size);
        val randomElement = list[randomIndex]
        list.remove(randomElement)
        return randomElement
    }



    private fun checkForDrawTwoPlayer(card: FunctionCard){
        if(card.getFunctionText.equals("Draw Two"))
        {
            for (i in 1..2) {
                val tempCard = getRandomCard(UnoCards.deck)
                UnoCards.deckEnemy.add(tempCard)
                UnoCards.deck.remove(tempCard)
            }
        }
    }
    private fun checkForDrawTwoEnemy(card: FunctionCard){
        if(card.getFunctionText.equals("Draw Two"))
        {
            for (i in 1..2) {
                val tempCard = getRandomCard(UnoCards.deck)
                UnoCards.deckPlayer.add(tempCard)
                UnoCards.deck.remove(tempCard)
            }
        }
    }

    fun enemyPlay() {
        //UnoCards.deckEnemy
        val lastCard = playedCards.last()
        var wasCardPlayed = false

        if(UnoCards.deckEnemy.size == 1){
            sayUno()
        }

        for (element in UnoCards.deckEnemy){
            if(element is FunctionCard){
                if(element.getCardColor.equals("Any")){
                    if (element.getFunctionText == "Choose Color") {
                        val tempCard = FunctionCard(CardColor.RED, "Choose Color"
                        )
                        //noch anzeigen was er sich wünscht, was er sich wünscht random machen
                        UnoCards.deckPlayer.remove(element)
                        playedCards.add(tempCard)
                        wasCardPlayed = true
                        break
                    }else{
                        val tempCard = FunctionCard(CardColor.RED, "Choose Color Draw Four"
                        )
                        for (i in 1..4) {
                            UnoCards.deckPlayer.add(getRandomCard(UnoCards.deck))
                        }
                        UnoCards.deckEnemy.remove(element)
                        playedCards.add(tempCard)
                        wasCardPlayed = true
                        break
                    }
                }
                else{
                    if(lastCard is FunctionCard){
                        if (lastCard.getCardColor == element.getCardColor || lastCard.getFunctionText.equals(element.getFunctionText) ) {
                            checkForDrawTwoEnemy(element)
                            UnoCards.deckEnemy.remove(element)
                            playedCards.add(element)
                            wasCardPlayed = true
                            break
                        }
                    }else if (lastCard is ValueCard){
                        if (lastCard.getCardColor == element.getCardColor) {
                            checkForDrawTwoEnemy(element)
                            UnoCards.deckEnemy.remove(element)
                            playedCards.add(element)
                            wasCardPlayed = true
                            break
                        }
                    }
                }
            }
        }

        if(!wasCardPlayed){
            for (element in UnoCards.deckEnemy){
                if(element is ValueCard){
                    if(lastCard is ValueCard){
                        if (lastCard.getCardColor == element.getCardColor) {
                            UnoCards.deckEnemy.remove(element)
                            playedCards.add(element)
                            wasCardPlayed = true
                            break
                        }
                    }else if(lastCard is FunctionCard){
                        if (lastCard.getCardColor == element.getCardColor) {
                            UnoCards.deckEnemy.remove(element)
                            playedCards.add(element)
                            wasCardPlayed = true
                            break
                        }
                    }
                }
            }
        }

        if(!wasCardPlayed){
            for (element in UnoCards.deckEnemy){
                if(element is ValueCard){
                    if(lastCard is ValueCard){
                        if (lastCard.getCardValue == element.getCardValue) {
                            UnoCards.deckEnemy.remove(element)
                            playedCards.add(element)
                            wasCardPlayed = true
                            break
                        }
                    }
                }
            }
        }

        if(!wasCardPlayed){
            val tempCard = getRandomCard(UnoCards.deck)
            UnoCards.deckEnemy.add(tempCard)
            UnoCards.deck.remove(tempCard)
        }
    }
}