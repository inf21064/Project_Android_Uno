package com.example.project_andorid_uno

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import kotlin.system.exitProcess
import com.example.project_andorid_uno.PlayedCards
import android.content.Context
import kotlin.random.Random

class RecyclerViewAdapter (val context: Context?, private val onItemClick:(PlayingCard) -> Unit,
                           val data: List<Int>, private val playedCardImageView: ImageView, val startingCard: PlayingCard) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>()
{
    val deckPlayer = UnoCards.deckPlayer

    val playedCards = PlayedCards(startingCard, context, playedCardImageView)

    inner class MyViewHolder(val row: View) : RecyclerView.ViewHolder(row) {
        val cardView = row.findViewById<ImageView>(R.id.cardView)
        fun bind(data: PlayingCard) {
            row.setOnClickListener{

                onItemClick.invoke(data)
                //reinhängen
                playedCards.playerPlay(data)

                playedCards.enemyPlay()
                Thread.sleep(1000)
                playedCards.updateImage(playedCards.playedCards.last().imageResId)
                notifyDataSetChanged()
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

fun getRandomCard(list: MutableList<PlayingCard>) : PlayingCard {
    val randomIndex = Random.nextInt(list.size);
    val randomElement = list[randomIndex]
    list.remove(randomElement)
    return randomElement
}