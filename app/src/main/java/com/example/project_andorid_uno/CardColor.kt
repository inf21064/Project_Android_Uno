package com.example.project_andorid_uno

enum class CardColor (){
    RED, YELLOW, GREEN, BLUE, ANY;

    override fun toString() : String {
        return when(this) {
            RED -> "red"
            YELLOW -> "yellow"
            GREEN -> "green"
            BLUE -> "blue"
            ANY -> "neutral"
        }
    }
}