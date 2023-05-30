package com.example.project_andorid_uno

abstract class PlayingCard(private val color: CardColor , val imageResId: Int) {
    var getCardColor : CardColor = color

    abstract fun getterImageResId(): Int
    override fun toString() : String = color.toString()


}