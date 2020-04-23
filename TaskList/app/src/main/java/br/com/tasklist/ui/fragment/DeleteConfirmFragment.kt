package br.com.tasklist.ui.fragment

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import br.com.tasklist.R
import br.com.tasklist.repository.data.entity.Task
import br.com.tasklist.util.Constants
import br.com.tasklist.viewmodel.TaskViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class DeleteConfirmFragment(private val cxt: Context) : DialogFragment(){
    private val viewModel by sharedViewModel<TaskViewModel>()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val task = arguments?.getParcelable<Task>(Constants.TASK) ?: Task()

        val builder = AlertDialog.Builder(cxt)
        builder.setTitle(R.string.warning)
        builder.setMessage(R.string.confirm_delete)
        builder.setPositiveButton(android.R.string.ok){_,_ -> viewModel.delete(task) }
        builder.setNegativeButton(android.R.string.cancel, null)
        return builder.create()
    }
}