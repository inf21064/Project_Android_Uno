package com.example.project_andorid_uno

import android.content.Context
import android.widget.Toast
import androidx.navigation.findNavController
import kotlin.random.Random

object UnoCards {
    val blueZero = ValueCard(CardColor.BLUE, 0, R.drawable.blue_0_card_clipart_md)
    val blueOne = ValueCard(CardColor.BLUE, 1, R.drawable.blue_1_card_clipart_md)
    val blueTwo = ValueCard(CardColor.BLUE, 2, R.drawable.blue_2_card_clipart_md)
    val blueThree = ValueCard(CardColor.BLUE, 3, R.drawable.blue_3_card_clipart_md)
    val blueFour = ValueCard(CardColor.BLUE, 4, R.drawable.blue_4_card_clipart_md)
    val blueFive = ValueCard(CardColor.BLUE, 5, R.drawable.blue_5_card_clipart_md)
    val blueSix = ValueCard(CardColor.BLUE, 6, R.drawable.blue_6_card_clipart_md)
    val blueSeven = ValueCard(CardColor.BLUE, 7, R.drawable.blue_7_card_clipart_md)
    val blueEight = ValueCard(CardColor.BLUE, 8, R.drawable.blue_8_card_clipart_md)
    val blueNine = ValueCard(CardColor.BLUE, 9, R.drawable.blue_9_card_clipart_md)
    val yellowZero = ValueCard(CardColor.YELLOW, 0, R.drawable.yellow_0_card_clipart_md)
    val yellowOne = ValueCard(CardColor.YELLOW, 1, R.drawable.yellow_1_card_clipart_md)
    val yellowTwo = ValueCard(CardColor.YELLOW, 2, R.drawable.yellow_2_card_clipart_md)
    val yellowThree = ValueCard(CardColor.YELLOW, 3, R.drawable.yellow_3_card_clipart_md)
    val yellowFour = ValueCard(CardColor.YELLOW, 4, R.drawable.yellow_4_card_clipart_md)
    val yellowFive = ValueCard(CardColor.YELLOW, 5, R.drawable.yellow_5_card_clipart_md)
    val yellowSix = ValueCard(CardColor.YELLOW, 6, R.drawable.yellow_6_card_clipart_md)
    val yellowSeven = ValueCard(CardColor.YELLOW, 7, R.drawable.yellow_7_card_clipart_md)
    val yellowEight = ValueCard(CardColor.YELLOW, 8, R.drawable.yellow_8_card_clipart_md)
    val yellowNine = ValueCard(CardColor.YELLOW, 9, R.drawable.yellow_9_card_clipart_md)
    val greenZero = ValueCard(CardColor.GREEN, 0, R.drawable.green_0_card_clipart_md)
    val greenOne = ValueCard(CardColor.GREEN, 1, R.drawable.green_1_card_clipart_md)
    val greenTwo = ValueCard(CardColor.GREEN, 2, R.drawable.green_2_card_clipart_md)
    val greenThree = ValueCard(CardColor.GREEN, 3, R.drawable.green_3_card_clipart_md)
    val greenFour = ValueCard(CardColor.GREEN, 4, R.drawable.green_4_card_clipart_md)
    val greenFive = ValueCard(CardColor.GREEN, 5, R.drawable.green_5_card_clipart_md)
    val greenSix = ValueCard(CardColor.GREEN, 6, R.drawable.green_6_card_clipart_md)
    val greenSeven = ValueCard(CardColor.GREEN, 7, R.drawable.green_7_card_clipart_md)
    val greenEight = ValueCard(CardColor.GREEN, 8, R.drawable.green_8_card_clipart_md)
    val greenNine = ValueCard(CardColor.GREEN, 9, R.drawable.green_9_card_clipart_md)
    val redZero = ValueCard(CardColor.RED, 0, R.drawable.red_0_card_clipart_md)
    val redOne = ValueCard(CardColor.RED, 1, R.drawable.red_1_card_clipart_md)
    val redTwo = ValueCard(CardColor.RED, 2, R.drawable.red_2_card_clipart_md)
    val redThree = ValueCard(CardColor.RED, 3, R.drawable.red_3_card_clipart_md)
    val redFour = ValueCard(CardColor.RED, 4, R.drawable.red_4_card_clipart_md)
    val redFive = ValueCard(CardColor.RED, 5, R.drawable.red_5_card_clipart_md)
    val redSix = ValueCard(CardColor.RED, 6, R.drawable.red_6_card_clipart_md)
    val redSeven = ValueCard(CardColor.RED, 7, R.drawable.red_7_card_clipart_md)
    val redEight = ValueCard(CardColor.RED, 8, R.drawable.red_8_card_clipart_md)
    val redNine = ValueCard(CardColor.RED, 9, R.drawable.red_9_card_clipart_md)
    val greenDrawTwo = FunctionCard(CardColor.GREEN, "Draw Two", R.drawable.green_draw_two_card_clipart_md)
    val yellowDrawTwo = FunctionCard(CardColor.YELLOW, "Draw Two", R.drawable.yellow_draw_two_card_clipart_md)
    val blueDrawTwo = FunctionCard(CardColor.BLUE, "Draw Two", R.drawable.blue_draw_two_card_clipart_md)
    val redDrawTwo = FunctionCard(CardColor.RED, "Draw Two", R.drawable.red_draw_two_card_clipart_md)
    val greenReverse = FunctionCard(CardColor.GREEN, "Reverse", R.drawable.green_reverse_card_clipart_md)
    val yellowReverse = FunctionCard(CardColor.YELLOW, "Reverse", R.drawable.yellow_reverse_card_clipart_md)
    val blueReverse = FunctionCard(CardColor.BLUE, "Reverse", R.drawable.blue_reverse_card_clipart_md)
    val redReverse = FunctionCard(CardColor.RED, "Reverse", R.drawable.red_reverse_card_clipart_md)
    val greenSkip = FunctionCard(CardColor.GREEN, "Skip", R.drawable.green_skip_card_clipart_md)
    val yellowSkip = FunctionCard(CardColor.YELLOW, "Skip", R.drawable.yellow_skip_card_clipart_md)
    val blueSkip = FunctionCard(CardColor.BLUE, "Skip", R.drawable.blue_skip_card_clipart_md)
    val redSkip = FunctionCard(CardColor.RED, "Skip", R.drawable.red_skip_card_clipart_md)
    val chooseColor = FunctionCard(CardColor.ANY, "Choose Color", R.drawable.wild_card_clipart_md)
    val plusFour = FunctionCard(CardColor.ANY, "Choose Color Draw Four", R.drawable.wild_draw_four_card_clipart_md)
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
        chooseColor, plusFour
    )

    var playDeck = deck


    fun getRandomCard(list: MutableList<PlayingCard>) : PlayingCard {

        val randomIndex = Random.nextInt(list.size);
        val randomElement = list[randomIndex]
        list.remove(randomElement)
        return randomElement
    }

    var deckPlayer:MutableList<PlayingCard> = mutableListOf()
    fun getPlayerDeck(numberOfCardsToDraw: Int){
        for(i in 1..numberOfCardsToDraw){
            val tempCard = getRandomCard(playDeck)
            deckPlayer.add(tempCard)
        }
    }

    var deckEnemy:MutableList<PlayingCard> = mutableListOf()
    fun getEnemyDeck(numberOfCardsToDraw: Int){
        for(i in 1..numberOfCardsToDraw){
            val tempCard = getRandomCard(playDeck)
            deckEnemy.add(tempCard)
        }
    }

    fun resetDeck(){
        playDeck = deck
    }
    fun getDeck(): String = deck.toString()


}