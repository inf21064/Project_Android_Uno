package com.example.project_andorid_uno

abstract class PlayingCard(private val _color: CardColor , val imageResId: Int) {
    var color : CardColor = _color

    abstract fun getterImageResId(): Int

    abstract fun copy(): PlayingCard
    override fun toString() : String = _color.toString()


}