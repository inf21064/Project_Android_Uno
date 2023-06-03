package com.example.project_andorid_uno

import android.content.Intent
import android.icu.number.IntegerWidth
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.project_andorid_uno.databinding.FragmentResultBinding

class ResultFragment : Fragment() {
    lateinit var result: ResultData
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val pointsPlayer = calculatePoints(UnoCards.deckEnemy)
        val pointsEnemy = calculatePoints(UnoCards.deckPlayer)
        result = ResultData("${pointsPlayer}","${pointsEnemy}")

        val emailIntent = Intent(Intent.ACTION_SEND)
        emailIntent.type = "text/plain"
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "UNO Result")

        val binding = DataBindingUtil.inflate<FragmentResultBinding>(inflater,
            R.layout.fragment_result,container,false)
        binding.resultData = result

        binding.shareResultButton?.setOnClickListener {
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Player Points:${pointsPlayer}" +
                    "\nBot Points: ${pointsEnemy}\n")
            startActivity(Intent.createChooser(emailIntent, "Send email..."))
        }
        binding.playAgainButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_resultFragment_to_gameFragment)
            val fragment = settingsFragment()
            UnoCards.playDeck.clear()
            UnoCards.refreshPlayDeck()
            UnoCards.deckPlayer.clear()
            UnoCards.getPlayerDeck(fragment.getNumOfCards())
            UnoCards.deckEnemy.clear()
            UnoCards.getEnemyDeck(fragment.getNumOfCards())
        }
        binding.returnHomeButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_resultFragment_to_homeFragment)
        }
        return binding.root
    }
    private fun calculatePoints(deck: MutableList<PlayingCard>): Int{
        var sum = 0

        for(element in deck){
            when(element){
                is ValueCard -> sum = sum
                is FunctionCard ->
                    if(element.getFunctionText == "Reverse"||element.getFunctionText == "Skip"||element.getFunctionText == "Draw Two"){
                        sum += 20
                    }else if(element.getFunctionText == "Choose Color" || element.getFunctionText == "Choose Color Draw Four"){
                        sum += 50
                    }
            }
        }
        return sum
    }
}