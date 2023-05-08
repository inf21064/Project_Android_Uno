package com.example.project_andorid_uno

class ValueCard(color: CardColor, private val value: Int) : PlayingCard(color) {
    val getCardValue: Int = value

    override fun toString(): String {
        return "${super.toString()}: $value"
    }
}