package br.com.mynotes.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import br.com.mynotes.R
import br.com.mynotes.data.domain.Note
import br.com.mynotes.ui.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.form_note_activity.view.*
import java.util.*

class FormDialogFragment : DialogFragment() {
    private lateinit var viewModel: NoteViewModel

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val note = arguments?.getParcelable<Note>("NOTE")!!

        val layout = LayoutInflater.from(requireContext())
            .inflate(R.layout.form_note_activity, null, false)
        layout.edNote.setText(note.description)

        val dialog = AlertDialog.Builder(requireContext())
        dialog.setView(layout)
        dialog.setTitle(if(note.id > 0) R.string.edit_note else R.string.new_note)
        dialog.setNegativeButton(R.string.cancel, null)
        dialog.setPositiveButton(R.string.save){ _, _ ->
            note.description = layout.edNote.text.toString()
            note.date = Calendar.getInstance()
            viewModel.save(note)
        }

        return dialog.create()
    }

    fun setVM(viewModel: NoteViewModel){
        this.viewModel = viewModel
    }
}