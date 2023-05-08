package com.example.project_andorid_uno

abstract class PlayingCard(private val color: CardColor) {
    val getCardColor : CardColor = color
    override fun toString() : String = color.toString()
}