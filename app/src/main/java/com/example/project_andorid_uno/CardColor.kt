package com.example.project_andorid_uno


enum class CardColor (){
    RED, YELLOW, GREEN, BLUE, ANY;

    override fun toString() : String {
        val context = MainActivity.instance.applicationContext
        return when(this) {
            RED -> context.getString(R.string.redColor)
            YELLOW -> context.getString(R.string.yellowColor)
            GREEN -> context.getString(R.string.greenColor)
            BLUE -> context.getString(R.string.blueColor)
            ANY -> "Any"
        }
    }
}