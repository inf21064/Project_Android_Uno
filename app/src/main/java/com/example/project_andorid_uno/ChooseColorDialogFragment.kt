package com.example.project_andorid_uno

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class ChooseColorDialogFragment : DialogFragment() {
    interface DialogCallback {
        fun onColorSelected(color: CardColor)
    }

    private var callback: DialogCallback? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val options = arrayOf(CardColor.RED.toString(), CardColor.BLUE.toString(), CardColor.GREEN.toString(), CardColor.YELLOW.toString())
        val builder = AlertDialog.Builder(context)

        builder.setTitle("Choose a color")
            .setSingleChoiceItems(options, -1) { dialog :DialogInterface, which:Int ->
                val selectedOption = options[which]
                /*when(selectedOption) {
                    CardColor.RED.toString() -> listener.onOptionSelected(CardColor.RED)
                    CardColor.BLUE.toString() -> listener.onOptionSelected(CardColor.BLUE)
                    CardColor.GREEN.toString() -> listener.onOptionSelected(CardColor.GREEN)
                    CardColor.YELLOW.toString() -> listener.onOptionSelected(CardColor.YELLOW)
                }*/
                callback?.onColorSelected(CardColor.valueOf(selectedOption))
                dialog.dismiss()
            }
        return builder.create()
    }

    fun setDialogCallback(callback: DialogCallback) {
        this.callback = callback
    }

}