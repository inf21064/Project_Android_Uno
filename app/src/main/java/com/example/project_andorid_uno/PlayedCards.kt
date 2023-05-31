package com.example.project_andorid_uno

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import kotlin.random.Random
import kotlin.system.exitProcess

class PlayedCards(val startCard:PlayingCard, val context: Context?, private val playedCardImageView: ImageView, private val gameFragment: GameFragment) {

    val playedCards: MutableList<PlayingCard> = mutableListOf(startCard)
    var whoHasTurn = "Player"
    private var _cardDrawn = false
    var saidUno = false
    var skipTurns = 0

    var cardDrawn: Boolean
        get() = _cardDrawn
        set(value) {
            gameFragment.changeEndTurnButtonVisibility(value)
            _cardDrawn = value
        }
    fun updateImage(imageResId: Int) {
        playedCardImageView.setImageResource(imageResId)
    }

    fun checkForSkipOrReverse(playedCard: FunctionCard){
        if(playedCard.getFunctionText =="Skip" || playedCard.getFunctionText == "Reverse"){
            skipTurns++
        }
    }

    fun checkForUno() {
        if (UnoCards.deckPlayer.size == 1 && !saidUno) {
            val message =
                "You forgot to say Uno. Draw 2 Cards!" // make string later for different languages
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
            for (i in 1..2) {

                if (UnoCards.playDeck.isEmpty()) {
                    val message =
                        "No more Cards, Game Over!" // make string later for different languages
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                    break
                } else {
                    val tempCard = getRandomCard(UnoCards.playDeck)
                    UnoCards.deckPlayer.add(tempCard)
                }
            }
        }
        saidUno = false
    }

    fun playerPlay(nextCard: PlayingCard) {
        if(skipTurns == 0) {
            val lastCard = playedCards.last()
            if (lastCard::class == nextCard::class) {
                when (nextCard) {
                    is ValueCard -> {
                        lastCard as ValueCard
                        if (lastCard.getCardColor == nextCard.getCardColor || lastCard.getCardValue == nextCard.getCardValue) {
                            UnoCards.deckPlayer.remove(nextCard)
                            checkForUno()
                            playedCards.add(nextCard)
                            whoHasTurn = "Enemy"
                        } else {
                            notAllowed(lastCard, nextCard)
                        }
                    }
                    is FunctionCard -> {
                        if (nextCard.getCardColor.toString() == "Any") {
                            checkForAny(nextCard)
                        } else if (lastCard is FunctionCard) {
                            if (lastCard.getCardColor == nextCard.getCardColor || lastCard.getFunctionText.equals(
                                    nextCard.getFunctionText
                                )
                            ) {
                                checkForSkipOrReverse(nextCard)
                                checkForDrawTwoPlayer(nextCard)
                                UnoCards.deckPlayer.remove(nextCard)
                                checkForUno()
                                playedCards.add(nextCard)
                                whoHasTurn = "Enemy"
                            } else {
                                notAllowed(lastCard, nextCard)
                            }
                        }
                    }
                    else -> {
                        println("Error with Cards: $lastCard and $nextCard")
                        exitProcess(0)
                    }
                }
            } else if (lastCard::class != nextCard::class) {
                when (nextCard) {
                    is ValueCard -> {
                        lastCard as FunctionCard
                        if (lastCard.getCardColor == nextCard.getCardColor) {
                            UnoCards.deckPlayer.remove(nextCard)
                            checkForUno()
                            playedCards.add(nextCard)
                            whoHasTurn = "Enemy"
                        } else {
                            notAllowed(lastCard, nextCard)
                        }
                    }
                    is FunctionCard -> {
                        lastCard as ValueCard
                        if (nextCard.getCardColor.toString() == "Any") {
                            checkForAny(nextCard)
                        } else if (lastCard.getCardColor == nextCard.getCardColor) {
                            checkForDrawTwoPlayer(nextCard)
                            checkForSkipOrReverse(nextCard)
                            UnoCards.deckPlayer.remove(nextCard)
                            checkForUno()
                            playedCards.add(nextCard)
                            whoHasTurn = "Enemy"
                        } else {
                            notAllowed(lastCard, nextCard)
                        }
                    }
                }
            }
            if (UnoCards.deckPlayer.isEmpty()) {
                val message = "You win!" // make string later for different languages
                Toast.makeText(context, message, Toast.LENGTH_LONG).show()
            }
        } else{
            skipTurns--
        }
    }

    private fun checkForAny(nextCard: FunctionCard) {
        if (nextCard.getFunctionText == "Choose Color") {
            val tempCard = FunctionCard(
                CardColor.RED/*whatever the player chooses*/,
                "Choose Color",
                R.drawable.wild_card_clipart_md
            )
            UnoCards.deckPlayer.remove(nextCard)
            checkForUno()
            playedCards.add(tempCard)
            whoHasTurn = "Enemy"
        } else {
            val tempCard = FunctionCard(
                CardColor.RED/*whatever the player chooses*/,
                "Choose Color Draw Four",
                R.drawable.wild_draw_four_card_clipart_md
            )
            for (i in 1..4) {
                if (UnoCards.playDeck.isEmpty()) {
                    val message =
                        "No more Cards, Game Over!" // make string later for different languages
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                    break
                }else{
                    val tempCard2 = getRandomCard(UnoCards.playDeck)
                    UnoCards.deckEnemy.add(tempCard2)
                }

            }
            UnoCards.deckPlayer.remove(nextCard)
            checkForUno()
            playedCards.add(tempCard)
            whoHasTurn = "Enemy"
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

                if(UnoCards.playDeck.isEmpty()){
                    val message = "No more Cards, Game Over!" // make string later for different languages
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                    break
                } else {
                    val tempCard = getRandomCard(UnoCards.playDeck)
                    UnoCards.deckEnemy.add(tempCard)
                }

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
                }else{
                    val tempCard = getRandomCard(UnoCards.playDeck)
                    UnoCards.deckPlayer.add(tempCard)
                }

            }
        }
    }

    fun chooseRandomColor(): CardColor{
        var color =CardColor.RED
        val randomIndex = Random.nextInt(4);
        when(randomIndex){
            0 -> color=CardColor.RED
            1 -> color=CardColor.YELLOW
            2 -> color=CardColor.GREEN
            3 -> color=CardColor.BLUE
        }
        val message = "I wish: ${color}"
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        return color
    }

    fun enemyPlay() {
        cardDrawn = false
        val lastCard = playedCards.last()
        var wasCardPlayed = false

        if(skipTurns != 0)
        {
            skipTurns--

        }else {
            for (element in UnoCards.deckEnemy) {
                if (element is FunctionCard) {
                    if (element.getCardColor == CardColor.ANY) {
                        if (element.getFunctionText == "Choose Color") {
                            val tempCard = FunctionCard(
                                chooseRandomColor(), "Choose Color", R.drawable.wild_card_clipart_md
                            )
                            UnoCards.deckEnemy.remove(element)
                            playedCards.add(tempCard)
                            wasCardPlayed = true
                            break
                        } else {
                            val tempCard = FunctionCard(
                                chooseRandomColor(),
                                "Choose Color Draw Four",
                                R.drawable.wild_draw_four_card_clipart_md
                            )
                            for (i in 1..4) {
                                if(UnoCards.playDeck.isEmpty()){
                                    val message = "No more cards.Game Over!"
                                    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                                    break
                                }else{
                                    UnoCards.deckPlayer.add(getRandomCard(UnoCards.playDeck))
                                }

                            }
                            UnoCards.deckEnemy.remove(element)
                            playedCards.add(tempCard)
                            wasCardPlayed = true
                            break
                        }
                    } else if (lastCard is FunctionCard) {
                        if (lastCard.getCardColor == element.getCardColor || lastCard.getFunctionText == element.getFunctionText)
                        {
                            checkForDrawTwoEnemy(element)
                            UnoCards.deckEnemy.remove(element)
                            checkForSkipOrReverse(element)
                            playedCards.add(element)
                            wasCardPlayed = true
                            break
                        }
                    } else if (lastCard is ValueCard) {
                        if (lastCard.getCardColor == element.getCardColor) {
                            checkForDrawTwoEnemy(element)
                            UnoCards.deckEnemy.remove(element)
                            checkForSkipOrReverse(element)
                            playedCards.add(element)
                            wasCardPlayed = true
                            break
                        }
                    }
                }
            }
            if (!wasCardPlayed) {
                for (element in UnoCards.deckEnemy) {
                    if (element is ValueCard) {
                        if (lastCard is ValueCard) {
                            if (lastCard.getCardColor == element.getCardColor) {
                                UnoCards.deckEnemy.remove(element)
                                playedCards.add(element)
                                wasCardPlayed = true
                                break
                            }
                        } else if (lastCard is FunctionCard) {
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

            if (!wasCardPlayed) {
                for (element in UnoCards.deckEnemy) {
                    if (element is ValueCard) {
                        if (lastCard is ValueCard) {
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
            whoHasTurn = "Player"

            if (!wasCardPlayed) {
                if(UnoCards.playDeck.isEmpty()){
                    val message = "No more cards.Game Over!"
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                }else{
                    val tempCard = getRandomCard(UnoCards.playDeck)
                    UnoCards.deckEnemy.add(tempCard)
                    val message = "I had to draw a card!" // make string later for different languages
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }

            }
            wasCardPlayed = false
            if (UnoCards.deckEnemy.isEmpty()) {
                val message = "You lose!" // make string later for different languages
                Toast.makeText(context, message, Toast.LENGTH_LONG).show()
            }
            if (UnoCards.deckEnemy.size == 1) {
                sayUno()
            }
        }

    }
}