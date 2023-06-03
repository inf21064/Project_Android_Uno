package com.example.project_andorid_uno

class ValueCard(color: CardColor, private val value: Int, imageResId: Int) : PlayingCard(color, imageResId) {
    val getCardValue: Int = value

    override fun getterImageResId(): Int {
        return imageResId
    }

    override fun copy(): ValueCard {
        return ValueCard(color, value, imageResId)
    }

    override fun toString(): String {
        return "${super.toString()}: $value"
    }
}