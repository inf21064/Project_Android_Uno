package com.example.project_andorid_uno

open class FunctionCard(color: CardColor, private val function: String) : PlayingCard(color) {
    val getFunctionText :String = function

    fun setCardColor(color: CardColor){
        super.getCardColor = color
    }

    override fun toString(): String {
        return "${super.toString()}: $function"
    }
}