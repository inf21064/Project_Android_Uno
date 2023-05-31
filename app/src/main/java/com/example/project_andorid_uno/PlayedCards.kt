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
    fun setCardColor(cardColor: CardColor){
        playedCards.last().color = cardColor
    }



    fun checkForUno() {
        if (UnoCards.deckPlayer.size == 1 && !saidUno) {
            val message =
                "You forgot to say Uno. Draw 2 Cards!" // make string later for different languages
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
            for (i in 1..2) {
                val tempCard = getRandomCard(UnoCards.playDeck)
                if (UnoCards.playDeck.isEmpty()) {
                    val message =
                        "No more Cards, Game Over!" // make string later for different languages
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                    break
                } else {
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
                        if (lastCard.color == nextCard.color || lastCard.getCardValue == nextCard.getCardValue) {
                            UnoCards.deckPlayer.remove(nextCard)
                            checkForUno()
                            playedCards.add(nextCard)
                            whoHasTurn = "Enemy"
                        } else {
                            notAllowed(lastCard, nextCard)
                        }
                    }
                    is FunctionCard -> {
                        if (nextCard.color.toString() == "Any") {
                            checkForAny(nextCard)
                        } else if (lastCard is FunctionCard) {
                            if (lastCard.color == nextCard.color || lastCard.getFunctionText.equals(
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
                        if (lastCard.color == nextCard.color) {
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
                        if (nextCard.color.toString() == "Any") {
                            checkForAny(nextCard)
                        } else if (lastCard.color == nextCard.color) {
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
    var playerChoosesColor : CardColor
        get() = CardColor.RED
        set(value) {
            playedCards.last().color = value
        }

    private fun checkForAny(nextCard: FunctionCard) {
        if (nextCard.getFunctionText == "Choose Color") {
            gameFragment.changeChooseColorVisibility(true)
            ///////////////////////////////////////////////////////////////
            val tempCard = FunctionCard(
                playerChoosesColor,
                "Choose Color",
                R.drawable.wild_card_clipart_md
            )
            UnoCards.deckPlayer.remove(nextCard)
            checkForUno()
            playedCards.add(tempCard)
            whoHasTurn = "Enemy"
        } else {
            gameFragment.changeChooseColorVisibility(true)
            val tempCard = FunctionCard(
                playerChoosesColor,
                "Choose Color Draw Four",
                R.drawable.wild_draw_four_card_clipart_md
            )
            for (i in 1..4) {
                if (UnoCards.playDeck.isEmpty()) {
                    val message =
                        "No more Cards, Game Over!" // make string later for different languages
                    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                    break
                }
                val tempCard2 = getRandomCard(UnoCards.playDeck)
                UnoCards.deckEnemy.add(tempCard2)
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
        if(list.isEmpty()){
            val message = "No more cards.Game Over!"
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
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
                } else {
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
                    if (element.color.toString() == "Any") {
                        if (element.getFunctionText == "Choose Color") {
                            val tempCard = FunctionCard(
                                CardColor.RED, "Choose Color", R.drawable.wild_card_clipart_md
                            )
                            //noch anzeigen was er sich wünscht, was er sich wünscht random machen
                            UnoCards.deckEnemy.remove(element)
                            playedCards.add(tempCard)
                            wasCardPlayed = true
                            break
                        } else {
                            val tempCard = FunctionCard(
                                CardColor.RED,
                                "Choose Color Draw Four",
                                R.drawable.wild_draw_four_card_clipart_md
                            )
                            for (i in 1..4) {
                                UnoCards.deckPlayer.add(getRandomCard(UnoCards.deck))
                            }
                            UnoCards.deckEnemy.remove(element)
                            playedCards.add(tempCard)
                            wasCardPlayed = true
                            break
                        }
                    } else if (lastCard is FunctionCard) {
                        if (lastCard.color == element.color || lastCard.getFunctionText == element.getFunctionText)
                        {
                            checkForDrawTwoEnemy(element)
                            UnoCards.deckEnemy.remove(element)
                            checkForSkipOrReverse(element)
                            playedCards.add(element)
                            wasCardPlayed = true
                            break
                        }
                    } else if (lastCard is ValueCard) {
                        if (lastCard.color == element.color) {
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
                            if (lastCard.color == element.color) {
                                UnoCards.deckEnemy.remove(element)
                                playedCards.add(element)
                                wasCardPlayed = true
                                break
                            }
                        } else if (lastCard is FunctionCard) {
                            if (lastCard.color == element.color) {
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
                val tempCard = getRandomCard(UnoCards.playDeck)
                UnoCards.deckEnemy.add(tempCard)
                val message = "I had to draw a card!" // make string later for different languages
                Toast.makeText(context, message, Toast.LENGTH_LONG).show()
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