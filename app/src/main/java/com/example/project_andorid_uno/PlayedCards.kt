package com.example.project_andorid_uno

import android.content.Context
import android.widget.Toast
import kotlin.random.Random
import kotlin.system.exitProcess

class PlayedCards(val startCard:ValueCard, val context: Context) {
    val playedCards: MutableList<PlayingCard> = mutableListOf(startCard)
    var whoHasTurn = "Player"

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
                            val tempCard = FunctionCard(CardColor.RED/*whatever the player chooses*/, "Choose Color", R.drawable.wild_card_clipart_md
                            )
                            UnoCards.deckPlayer.remove(nextCard)
                            playedCards.add(tempCard)
                        } else {
                            val tempCard = FunctionCard(CardColor.RED/*whatever the player chooses*/, "Choose Color Draw Four", R.drawable.wild_draw_four_card_clipart_md
                            )
                            for (i in 1..4) {
                                if(UnoCards.playDeck.isEmpty()){
                                    val message = "No more Cards, Game Over!" // make string later for different languages
                                    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                                    break
                                }
                                val tempCard2 = getRandomCard(UnoCards.playDeck)
                                UnoCards.deckEnemy.add(tempCard2)
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
        if(UnoCards.deckPlayer.isEmpty()){
            val message = "You win!" // make string later for different languages
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
        var whoHasTurn = "Enemy"
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
                val tempCard = getRandomCard(UnoCards.playDeck)
                if(UnoCards.playDeck.isEmpty()){
                    val message = "No more Cards, Game Over!" // make string later for different languages
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                    break
                }
                UnoCards.deckEnemy.add(tempCard)
            }
        }
    }
    private fun checkForDrawTwoEnemy(card: FunctionCard){
        if(card.getFunctionText.equals("Draw Two"))
        {
            for (i in 1..2) {
                if(UnoCards.playDeck.isEmpty()){
                    val message = "No more Cards, Game Over!" // make string later for different languages
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                    break
                }
                val tempCard = getRandomCard(UnoCards.playDeck)
                UnoCards.deckPlayer.add(tempCard)
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
                        val tempCard = FunctionCard(CardColor.RED, "Choose Color", R.drawable.wild_card_clipart_md
                        )
                        //noch anzeigen was er sich wünscht, was er sich wünscht random machen
                        UnoCards.deckPlayer.remove(element)
                        playedCards.add(tempCard)
                        wasCardPlayed = true
                        break
                    }else{
                        val tempCard = FunctionCard(CardColor.RED, "Choose Color Draw Four", R.drawable.wild_draw_four_card_clipart_md
                        )
                        for (i in 1..4) {
                            UnoCards.deckPlayer.add(getRandomCard(UnoCards.deck))
                            if(UnoCards.playDeck.isEmpty()){
                                val message = "No more Cards, Game Over!" // make string later for different languages
                                Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                                break
                            }
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
            val tempCard = getRandomCard(UnoCards.playDeck)
            UnoCards.deckEnemy.add(tempCard)
        }
        if(UnoCards.deckEnemy.isEmpty()){
            val message = "You lose!" // make string later for different languages
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
        var whoHasTurn = "Player"
    }
}