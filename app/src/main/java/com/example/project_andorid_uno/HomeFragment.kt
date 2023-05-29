package com.example.project_andorid_uno

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.project_andorid_uno.databinding.FragmentHomeBinding
import com.example.project_andorid_uno.settingsFragment

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater,
            R.layout.fragment_home,container,false)

        binding.playBtn.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_homeFragment_to_gameFragment)
            val fragment = settingsFragment()
            UnoCards.playDeck = UnoCards.deck
            UnoCards.deckPlayer.clear()
            UnoCards.getPlayerDeck(fragment.getNumOfCards())
            UnoCards.deckEnemy.clear()
            UnoCards.getEnemyDeck(fragment.getNumOfCards())
        }
        setHasOptionsMenu(true)
        return binding.root
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}