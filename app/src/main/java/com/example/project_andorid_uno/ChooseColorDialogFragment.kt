package com.example.project_andorid_uno

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class ChooseColorDialogFragment : DialogFragment() {
    private lateinit var listener: OnOptionSelectedListener
    interface OnOptionSelectedListener {
        fun onOptionSelected(color: CardColor)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val options = arrayOf(CardColor.RED.toString(), CardColor.BLUE.toString(), CardColor.GREEN.toString(), CardColor.YELLOW.toString())
        val builder = AlertDialog.Builder(context)

        builder.setTitle("Choose a color")
            .setSingleChoiceItems(options, -1) { dialog :DialogInterface, which:Int ->
                val selectedOption = options[which]
                when(selectedOption) {
                    CardColor.RED.toString() -> listener.onOptionSelected(CardColor.RED)
                    CardColor.BLUE.toString() -> listener.onOptionSelected(CardColor.BLUE)
                    CardColor.GREEN.toString() -> listener.onOptionSelected(CardColor.GREEN)
                    CardColor.YELLOW.toString() -> listener.onOptionSelected(CardColor.YELLOW)
                }
                dialog.dismiss()
            }
        return builder.create()
    }

    fun setOnOptionSelectedListener(listener: OnOptionSelectedListener) {
        this.listener = listener
    }

}