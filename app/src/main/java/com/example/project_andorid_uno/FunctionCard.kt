package com.example.project_andorid_uno

open class FunctionCard(color: CardColor, private val function: String, imageResId: Int) : PlayingCard(color, imageResId) {
    val getFunctionText :String = function

    fun setCardColor(color: CardColor){
        super.color = color
    }

    override fun getterImageResId(): Int {
        return imageResId
    }

    override fun toString(): String {
        return "${super.toString()}: $function"
    }
}