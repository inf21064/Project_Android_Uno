package com.example.project_andorid_uno

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.project_andorid_uno.databinding.FragmentChoosecolorBinding

class ChooseColorFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentChoosecolorBinding>(inflater,
            R.layout.fragment_choosecolor,container,false)
        lateinit var chosenColor: CardColor
        lateinit var tempListOfPlayedCards: MutableList<PlayingCard>
        val sourceFragmentId = R.id.gameFragment
        val sendedList = arguments?.get("playedCards") as MutableList<PlayingCard>?
        if (sendedList != null)
            tempListOfPlayedCards = sendedList


        binding.radioGroupChooseColor.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId) {
                R.id.radioButtonRed -> chosenColor = CardColor.RED
                R.id.radioButtonBlue -> chosenColor = CardColor.BLUE
                R.id.radioButtonGreen -> chosenColor = CardColor.GREEN
                R.id.radioButtonYellow -> chosenColor = CardColor.YELLOW
            }
            val bundle = bundleOf("chosenColor" to chosenColor, "playedCards" to tempListOfPlayedCards)
            this.findNavController().apply {
                previousBackStackEntry?.savedStateHandle?.set("returnArgumentsBundle", bundle)
                popBackStack(sourceFragmentId, false)
            }
        }
        return binding.root
    }
}