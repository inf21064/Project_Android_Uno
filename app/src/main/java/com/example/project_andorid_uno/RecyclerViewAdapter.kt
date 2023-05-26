package com.example.project_andorid_uno

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.recyclerview.widget.RecyclerView
import com.example.project_andorid_uno.databinding.FragmentGameBinding
import kotlin.system.exitProcess

class RecyclerViewAdapter (private val onItemClick:(UnoCard) -> Unit,
                           val data: List<Int>) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>()
{
    val UnoCards = mutableListOf(UnoCard(R.drawable.green_0_card_clipart_md),
        UnoCard(R.drawable.green_1_card_clipart_md),
        UnoCard(R.drawable.green_2_card_clipart_md),
        UnoCard(R.drawable.green_reverse_card_clipart_md),
        UnoCard(R.drawable.blue_reverse_card_clipart_md),
        UnoCard(R.drawable.blue_5_card_clipart_md),
        UnoCard(R.drawable.blue_skip_card_clipart_md),
        UnoCard(R.drawable.blue_4_card_clipart_md),
        UnoCard(R.drawable.red_0_card_clipart_md),
        UnoCard(R.drawable.red_2_card_clipart_md),
        UnoCard(R.drawable.green_5_card_clipart_md),
        UnoCard(R.drawable.wild_card_clipart_md),
        UnoCard(R.drawable.red_4_card_clipart_md),
        UnoCard(R.drawable.green_3_card_clipart_md),
        UnoCard(R.drawable.blue_9_card_clipart_md),
        UnoCard(R.drawable.yellow_4_card_clipart_md))

    inner class MyViewHolder(val row: View) : RecyclerView.ViewHolder(row) {
        val cardView = row.findViewById<ImageView>(R.id.cardView)
        fun bind(data: UnoCard) {
            row.setOnClickListener{
                onItemClick.invoke(data)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)
        return MyViewHolder(layout)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.cardView.setImageResource(getUnoCard(position).imageResId)
        holder.bind(UnoCards[position])
    }
    override fun getItemCount(): Int = data.size

    private fun getUnoCard(cardId : Int) : UnoCard {
        if (cardId in 0..15)
            return UnoCards[cardId]
        else  {
                println("Cant select image")
                exitProcess(0)
        }
    }
}