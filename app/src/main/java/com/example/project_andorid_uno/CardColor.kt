package com.example.project_andorid_uno

enum class CardColor (){
    RED, YELLOW, GREEN, BLUE, ANY;

    override fun toString() : String {
        return when(this) {
            RED -> R.string.redColor.toString()
            YELLOW -> R.string.yellowColor.toString()
            GREEN -> R.string.greenColor.toString()
            BLUE -> R.string.blueColor.toString()
            ANY -> "Any"
        }
    }
}