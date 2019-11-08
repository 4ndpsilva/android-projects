package br.com.monisoftware.tasklist.view.fragment

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import br.com.monisoftware.tasklist.R
import br.com.monisoftware.tasklist.presenter.contract.ListContract
import br.com.monisoftware.tasklist.util.Constants

class DeleteConfirmFragment(private val cxt: Context) : DialogFragment(){
    private lateinit var listener: ListContract.DeleteListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val id = arguments?.getLong(Constants.ID) ?: 0
        val builder = AlertDialog.Builder(cxt)
        builder.setTitle(R.string.warning)
        builder.setMessage(R.string.confirm_delete)
        builder.setPositiveButton(android.R.string.ok){_,_ -> listener.setConfirm(true, id)}
        builder.setNegativeButton(android.R.string.cancel){_,_ -> listener.setConfirm(false, id)}
        return builder.create()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as ListContract.DeleteListener
    }
}