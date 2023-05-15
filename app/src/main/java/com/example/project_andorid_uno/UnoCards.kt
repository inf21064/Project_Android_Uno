package com.example.project_andorid_uno

import android.content.Context
import android.widget.Toast
import kotlin.random.Random

object UnoCards {
    val coverCard = CoverCard
    val blueZero = ValueCard(CardColor.BLUE, 0)
    val blueOne = ValueCard(CardColor.BLUE, 1)
    val blueTwo = ValueCard(CardColor.BLUE, 2)
    val blueThree = ValueCard(CardColor.BLUE, 3)
    val blueFour = ValueCard(CardColor.BLUE, 4)
    val blueFive = ValueCard(CardColor.BLUE, 5)
    val blueSix = ValueCard(CardColor.BLUE, 6)
    val blueSeven = ValueCard(CardColor.BLUE, 7)
    val blueEight = ValueCard(CardColor.BLUE, 8)
    val blueNine = ValueCard(CardColor.BLUE, 9)
    val yellowZero = ValueCard(CardColor.YELLOW, 0)
    val yellowOne = ValueCard(CardColor.YELLOW, 1)
    val yellowTwo = ValueCard(CardColor.YELLOW, 2)
    val yellowThree = ValueCard(CardColor.YELLOW, 3)
    val yellowFour = ValueCard(CardColor.YELLOW, 4)
    val yellowFive = ValueCard(CardColor.YELLOW, 5)
    val yellowSix = ValueCard(CardColor.YELLOW, 6)
    val yellowSeven = ValueCard(CardColor.YELLOW, 7)
    val yellowEight = ValueCard(CardColor.YELLOW, 8)
    val yellowNine = ValueCard(CardColor.YELLOW, 9)
    val greenZero = ValueCard(CardColor.GREEN, 0)
    val greenOne = ValueCard(CardColor.GREEN, 1)
    val greenTwo = ValueCard(CardColor.GREEN, 2)
    val greenThree = ValueCard(CardColor.GREEN, 3)
    val greenFour = ValueCard(CardColor.GREEN, 4)
    val greenFive = ValueCard(CardColor.GREEN, 5)
    val greenSix = ValueCard(CardColor.GREEN, 6)
    val greenSeven = ValueCard(CardColor.GREEN, 7)
    val greenEight = ValueCard(CardColor.GREEN, 8)
    val greenNine = ValueCard(CardColor.GREEN, 9)
    val redZero = ValueCard(CardColor.RED, 0)
    val redOne = ValueCard(CardColor.RED, 1)
    val redTwo = ValueCard(CardColor.RED, 2)
    val redThree = ValueCard(CardColor.RED, 3)
    val redFour = ValueCard(CardColor.RED, 4)
    val redFive = ValueCard(CardColor.RED, 5)
    val redSix = ValueCard(CardColor.RED, 6)
    val redSeven = ValueCard(CardColor.RED, 7)
    val redEight = ValueCard(CardColor.RED, 8)
    val redNine = ValueCard(CardColor.RED, 9)
    val greenDrawTwo = FunctionCard(CardColor.GREEN, "Draw Two")
    val yellowDrawTwo = FunctionCard(CardColor.YELLOW, "Draw Two")
    val blueDrawTwo = FunctionCard(CardColor.BLUE, "Draw Two")
    val redDrawTwo = FunctionCard(CardColor.RED, "Draw Two")
    val greenReverse = FunctionCard(CardColor.GREEN, "Reverse")
    val yellowReverse = FunctionCard(CardColor.YELLOW, "Reverse")
    val blueReverse = FunctionCard(CardColor.BLUE, "Reverse")
    val redReverse = FunctionCard(CardColor.RED, "Reverse")
    val greenSkip = FunctionCard(CardColor.GREEN, "Skip")
    val yellowSkip = FunctionCard(CardColor.YELLOW, "Skip")
    val blueSkip = FunctionCard(CardColor.BLUE, "Skip")
    val redSkip = FunctionCard(CardColor.RED, "Skip")
    val chooseColor = FunctionCard(CardColor.ANY, "Choose Color")
    val plusFour = FunctionCard(CardColor.ANY, "Choose Color Draw Four")
    //draw cards(button with getcard function und skip turn)
    // , switch cards, remove cards


    val deck = mutableListOf(
        yellowZero, yellowOne, yellowTwo, yellowThree, yellowFour, yellowFive, yellowSix, yellowSeven, yellowEight, yellowNine,
        greenZero, greenOne, greenTwo, greenThree, greenFour, greenFive, greenSix, greenSeven, greenEight, greenNine,
        blueZero, blueOne, blueTwo, blueThree, blueFour, blueFive, blueSix, blueSeven, blueEight, blueNine,
        redZero, redOne, redTwo, redThree, redFour, redFive, redSix,  redSeven, redEight, redNine,
        greenDrawTwo, yellowDrawTwo, redDrawTwo, blueDrawTwo,
        yellowSkip, greenSkip, blueSkip, redSkip,
        yellowReverse, greenReverse, blueReverse, redReverse,
        chooseColor
    )



    private fun getRandomCard(list: MutableList<PlayingCard>) : PlayingCard {
        val randomIndex = Random.nextInt(list.size);
        val randomElement = list[randomIndex]
        list.remove(randomElement)
        return randomElement
    }

    var deckPlayer:MutableList<PlayingCard> = mutableListOf()
    fun getPlayerDeck(){
        for(i in 1..7){
            deckPlayer.add(getRandomCard(deck))
        }
    }

    var deckEnemy:MutableList<PlayingCard> = mutableListOf()
    fun getEnemyDeck(numberOfCardsToDraw: Int){
        for(i in 1..numberOfCardsToDraw){ // make second number depending on user input to get requirement
            deckEnemy.add(getRandomCard(deck))
        }
    }

    fun getStack(): String = deck.toString()

    fun play(context: Context): Unit {
        val game = PlayedCards(greenNine, context)  // have to implement a random starting card

        while(!(deckPlayer.isEmpty()) && !(deckEnemy.isEmpty()))
        {
            //game.play(element) // which element should be inserted? probably the one the player plays
            if(deckPlayer.isEmpty())
            {
                val message = "You win!" // make string later for different languages
                Toast.makeText(context, message, Toast.LENGTH_LONG).show()
            }
            // enemy algorithm
            if(deckEnemy.isEmpty())
            {
                val message = "You lose!" // make string later for different languages
                Toast.makeText(context, message, Toast.LENGTH_LONG).show()
            }
        }
    }
}