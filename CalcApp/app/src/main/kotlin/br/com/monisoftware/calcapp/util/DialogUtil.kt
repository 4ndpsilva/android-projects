package br.com.monisoftware.calcapp.util

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import br.com.monisoftware.calcapp.R

typealias EventDialog = (DialogInterface, Int) -> Unit

object DialogUtil{
    fun showMessage(context: Context, msg: Int){
        val dialog = createBaseDialog(context, msg, R.string.warning)
        dialog.setNeutralButton("OK", null)
        dialog.show()
    }

    fun showConfirm(context: Context, msg: Int){
        val cxt = context as Activity
         val func: EventDialog = { d, w -> cxt.finish() }

        val dialog = createBaseDialog(context, msg, R.string.warning)
        dialog.setPositiveButton(R.string.yes, func)
        dialog.setNegativeButton(R.string.no, null)
        dialog.show()
    }

    private fun createBaseDialog(context: Context, msg: Int, title: Int = 0): AlertDialog.Builder{
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(msg)
        return builder
    }
}