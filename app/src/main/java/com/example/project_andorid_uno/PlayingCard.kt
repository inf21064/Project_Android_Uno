package com.example.project_andorid_uno

abstract class PlayingCard(var color: CardColor, val imageResId: Int) {
    var getCardColor : CardColor = color

    abstract fun getterImageResId(): Int

    abstract fun copy(): PlayingCard
    override fun toString() : String = color.toString()


}