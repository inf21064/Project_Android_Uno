package com.example.project_andorid_uno

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import kotlin.system.exitProcess

class RecyclerViewAdapter (private val onItemClick:(PlayingCard) -> Unit,
                           val data: List<Int>) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>()
{
    val deckPlayer = UnoCards.deckPlayer

    inner class MyViewHolder(val row: View) : RecyclerView.ViewHolder(row) {
        val cardView = row.findViewById<ImageView>(R.id.cardView)
        fun bind(data: PlayingCard) {
            row.setOnClickListener{
                onItemClick.invoke(data)
                //reinhängen


            }
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)
        return MyViewHolder(layout)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.cardView.setImageResource(getUnoCard(position).getterImageResId())
        holder.bind(deckPlayer[position])
    }
    override fun getItemCount(): Int = data.size

    private fun getUnoCard(cardId : Int) : PlayingCard {
        if (cardId in 0..UnoCards.deckPlayer.size-1)
            return deckPlayer[cardId]
        else  {
                println("Cant select image")
                exitProcess(0)
        }
    }
}