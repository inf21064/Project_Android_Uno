package com.example.project_andorid_uno

import android.content.Context
import android.widget.Toast
import androidx.navigation.findNavController
import kotlin.random.Random

object UnoCards {
    private val blueZero = ValueCard(CardColor.BLUE, 0, R.drawable.blue_0_card_clipart_md)
    private val blueOne = ValueCard(CardColor.BLUE, 1, R.drawable.blue_1_card_clipart_md)
    private val blueTwo = ValueCard(CardColor.BLUE, 2, R.drawable.blue_2_card_clipart_md)
    private val blueThree = ValueCard(CardColor.BLUE, 3, R.drawable.blue_3_card_clipart_md)
    private val blueFour = ValueCard(CardColor.BLUE, 4, R.drawable.blue_4_card_clipart_md)
    private val blueFive = ValueCard(CardColor.BLUE, 5, R.drawable.blue_5_card_clipart_md)
    private val blueSix = ValueCard(CardColor.BLUE, 6, R.drawable.blue_6_card_clipart_md)
    private val blueSeven = ValueCard(CardColor.BLUE, 7, R.drawable.blue_7_card_clipart_md)
    private val blueEight = ValueCard(CardColor.BLUE, 8, R.drawable.blue_8_card_clipart_md)
    private val blueNine = ValueCard(CardColor.BLUE, 9, R.drawable.blue_9_card_clipart_md)
    private val yellowZero = ValueCard(CardColor.YELLOW, 0, R.drawable.yellow_0_card_clipart_md)
    private val yellowOne = ValueCard(CardColor.YELLOW, 1, R.drawable.yellow_1_card_clipart_md)
    private val yellowTwo = ValueCard(CardColor.YELLOW, 2, R.drawable.yellow_2_card_clipart_md)
    private val yellowThree = ValueCard(CardColor.YELLOW, 3, R.drawable.yellow_3_card_clipart_md)
    private val yellowFour = ValueCard(CardColor.YELLOW, 4, R.drawable.yellow_4_card_clipart_md)
    private val yellowFive = ValueCard(CardColor.YELLOW, 5, R.drawable.yellow_5_card_clipart_md)
    private val yellowSix = ValueCard(CardColor.YELLOW, 6, R.drawable.yellow_6_card_clipart_md)
    private val yellowSeven = ValueCard(CardColor.YELLOW, 7, R.drawable.yellow_7_card_clipart_md)
    private val yellowEight = ValueCard(CardColor.YELLOW, 8, R.drawable.yellow_8_card_clipart_md)
    private val yellowNine = ValueCard(CardColor.YELLOW, 9, R.drawable.yellow_9_card_clipart_md)
    private val greenZero = ValueCard(CardColor.GREEN, 0, R.drawable.green_0_card_clipart_md)
    private val greenOne = ValueCard(CardColor.GREEN, 1, R.drawable.green_1_card_clipart_md)
    private val greenTwo = ValueCard(CardColor.GREEN, 2, R.drawable.green_2_card_clipart_md)
    private val greenThree = ValueCard(CardColor.GREEN, 3, R.drawable.green_3_card_clipart_md)
    private val greenFour = ValueCard(CardColor.GREEN, 4, R.drawable.green_4_card_clipart_md)
    private val greenFive = ValueCard(CardColor.GREEN, 5, R.drawable.green_5_card_clipart_md)
    private val greenSix = ValueCard(CardColor.GREEN, 6, R.drawable.green_6_card_clipart_md)
    private val greenSeven = ValueCard(CardColor.GREEN, 7, R.drawable.green_7_card_clipart_md)
    private val greenEight = ValueCard(CardColor.GREEN, 8, R.drawable.green_8_card_clipart_md)
    private val greenNine = ValueCard(CardColor.GREEN, 9, R.drawable.green_9_card_clipart_md)
    private val redZero = ValueCard(CardColor.RED, 0, R.drawable.red_0_card_clipart_md)
    private val redOne = ValueCard(CardColor.RED, 1, R.drawable.red_1_card_clipart_md)
    private val redTwo = ValueCard(CardColor.RED, 2, R.drawable.red_2_card_clipart_md)
    private val redThree = ValueCard(CardColor.RED, 3, R.drawable.red_3_card_clipart_md)
    private val redFour = ValueCard(CardColor.RED, 4, R.drawable.red_4_card_clipart_md)
    private val redFive = ValueCard(CardColor.RED, 5, R.drawable.red_5_card_clipart_md)
    private val redSix = ValueCard(CardColor.RED, 6, R.drawable.red_6_card_clipart_md)
    private val redSeven = ValueCard(CardColor.RED, 7, R.drawable.red_7_card_clipart_md)
    private val redEight = ValueCard(CardColor.RED, 8, R.drawable.red_8_card_clipart_md)
    private val redNine = ValueCard(CardColor.RED, 9, R.drawable.red_9_card_clipart_md)
    private val greenDrawTwo = FunctionCard(CardColor.GREEN, "Draw Two", R.drawable.green_draw_two_card_clipart_md)
    private val yellowDrawTwo = FunctionCard(CardColor.YELLOW, "Draw Two", R.drawable.yellow_draw_two_card_clipart_md)
    private val blueDrawTwo = FunctionCard(CardColor.BLUE, "Draw Two", R.drawable.blue_draw_two_card_clipart_md)
    private val redDrawTwo = FunctionCard(CardColor.RED, "Draw Two", R.drawable.red_draw_two_card_clipart_md)
    private val greenReverse = FunctionCard(CardColor.GREEN, "Reverse", R.drawable.green_reverse_card_clipart_md)
    private val yellowReverse = FunctionCard(CardColor.YELLOW, "Reverse", R.drawable.yellow_reverse_card_clipart_md)
    private val blueReverse = FunctionCard(CardColor.BLUE, "Reverse", R.drawable.blue_reverse_card_clipart_md)
    private val redReverse = FunctionCard(CardColor.RED, "Reverse", R.drawable.red_reverse_card_clipart_md)
    private val greenSkip = FunctionCard(CardColor.GREEN, "Skip", R.drawable.green_skip_card_clipart_md)
    private val yellowSkip = FunctionCard(CardColor.YELLOW, "Skip", R.drawable.yellow_skip_card_clipart_md)
    private val blueSkip = FunctionCard(CardColor.BLUE, "Skip", R.drawable.blue_skip_card_clipart_md)
    private val redSkip = FunctionCard(CardColor.RED, "Skip", R.drawable.red_skip_card_clipart_md)
    private val chooseColor = FunctionCard(CardColor.ANY, "Choose Color", R.drawable.wild_card_clipart_md)
    private val plusFour = FunctionCard(CardColor.ANY, "Choose Color Draw Four", R.drawable.wild_draw_four_card_clipart_md)
    //draw cards(button with getcard function und skip turn)
    // , switch cards, remove cards

    fun refreshPlayDeck(){
        if(!playDeck.contains(yellowZero))
        {
            playDeck.add(yellowZero)
        }
        if(!playDeck.contains(yellowOne))
        {
            playDeck.add(yellowOne)
        }
        if(!playDeck.contains(yellowTwo))
        {
            playDeck.add(yellowTwo)
        }
        if(!playDeck.contains(yellowThree))
        {
            playDeck.add(yellowThree)
        }
        if(!playDeck.contains(yellowFour))
        {
            playDeck.add(yellowFour)
        }
        if(!playDeck.contains(yellowFive))
        {
            playDeck.add(yellowFive)
        }
        if(!playDeck.contains(yellowSix))
        {
            playDeck.add(yellowSix)
        }
        if(!playDeck.contains(yellowSeven))
        {
            playDeck.add(yellowSeven)
        }
        if(!playDeck.contains(yellowEight))
        {
            playDeck.add(yellowEight)
        }
        if(!playDeck.contains(yellowNine))
        {
            playDeck.add(yellowNine)
        }
        if(!playDeck.contains(yellowReverse))
        {
            playDeck.add(yellowReverse)
        }
        if(!playDeck.contains(yellowSkip))
        {
            playDeck.add(yellowSkip)
        }
        if(!playDeck.contains(yellowDrawTwo))
        {
            playDeck.add(yellowDrawTwo)
        }
        if(!playDeck.contains(greenZero))
        {
            playDeck.add(greenZero)
        }
        if(!playDeck.contains(greenOne))
        {
            playDeck.add(greenOne)
        }
        if(!playDeck.contains(greenTwo))
        {
            playDeck.add(greenTwo)
        }
        if(!playDeck.contains(greenThree))
        {
            playDeck.add(greenThree)
        }
        if(!playDeck.contains(greenFour))
        {
            playDeck.add(greenFour)
        }
        if(!playDeck.contains(greenFive))
        {
            playDeck.add(greenFive)
        }
        if(!playDeck.contains(greenSix))
        {
            playDeck.add(greenSix)
        }
        if(!playDeck.contains(greenSeven))
        {
            playDeck.add(greenSeven)
        }
        if(!playDeck.contains(greenEight))
        {
            playDeck.add(greenEight)
        }
        if(!playDeck.contains(greenNine))
        {
            playDeck.add(greenNine)
        }
        if(!playDeck.contains(greenReverse))
        {
            playDeck.add(greenReverse)
        }
        if(!playDeck.contains(greenSkip))
        {
            playDeck.add(greenSkip)
        }
        if(!playDeck.contains(greenDrawTwo))
        {
            playDeck.add(greenDrawTwo)
        }
        if(!playDeck.contains(redZero))
        {
            playDeck.add(redZero)
        }
        if(!playDeck.contains(redOne))
        {
            playDeck.add(redOne)
        }
        if(!playDeck.contains(redTwo))
        {
            playDeck.add(redTwo)
        }
        if(!playDeck.contains(redThree))
        {
            playDeck.add(redThree)
        }
        if(!playDeck.contains(redFour))
        {
            playDeck.add(redFour)
        }
        if(!playDeck.contains(redFive))
        {
            playDeck.add(redFive)
        }
        if(!playDeck.contains(redSix))
        {
            playDeck.add(redSix)
        }
        if(!playDeck.contains(redSeven))
        {
            playDeck.add(redSeven)
        }
        if(!playDeck.contains(redEight))
        {
            playDeck.add(redEight)
        }
        if(!playDeck.contains(redNine))
        {
            playDeck.add(redNine)
        }
        if(!playDeck.contains(redReverse))
        {
            playDeck.add(redReverse)
        }
        if(!playDeck.contains(redSkip))
        {
            playDeck.add(redSkip)
        }
        if(!playDeck.contains(redDrawTwo))
        {
            playDeck.add(redDrawTwo)
        }
        if(!playDeck.contains(blueZero))
        {
            playDeck.add(blueZero)
        }
        if(!playDeck.contains(blueOne))
        {
            playDeck.add(blueOne)
        }
        if(!playDeck.contains(blueTwo))
        {
            playDeck.add(blueTwo)
        }
        if(!playDeck.contains(blueThree))
        {
            playDeck.add(blueThree)
        }
        if(!playDeck.contains(blueFour))
        {
            playDeck.add(blueFour)
        }
        if(!playDeck.contains(blueFive))
        {
            playDeck.add(blueFive)
        }
        if(!playDeck.contains(blueSix))
        {
            playDeck.add(blueSix)
        }
        if(!playDeck.contains(blueSeven))
        {
            playDeck.add(blueSeven)
        }
        if(!playDeck.contains(blueEight))
        {
            playDeck.add(blueEight)
        }
        if(!playDeck.contains(blueNine))
        {
            playDeck.add(blueNine)
        }
        if(!playDeck.contains(blueReverse))
        {
            playDeck.add(blueReverse)
        }
        if(!playDeck.contains(blueSkip))
        {
            playDeck.add(blueSkip)
        }
        if(!playDeck.contains(blueDrawTwo))
        {
            playDeck.add(blueDrawTwo)
        }
        if(!playDeck.contains(chooseColor))
        {
            playDeck.add(chooseColor)
        }
        if(!playDeck.contains(plusFour))
        {
            playDeck.add(plusFour)
        }
    }

    var playDeck = mutableListOf<PlayingCard>()

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
}