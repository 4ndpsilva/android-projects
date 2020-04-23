package br.com.tasklist.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import br.com.tasklist.R
import br.com.tasklist.repository.data.entity.Task
import br.com.tasklist.databinding.ActivityFormBinding
import br.com.tasklist.util.Constants
import br.com.tasklist.util.toast
import br.com.tasklist.viewmodel.TaskViewModel
import kotlinx.android.synthetic.main.activity_form.*
import kotlinx.android.synthetic.main.app_bar.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FormActivity : AppCompatActivity(){
    private val viewModel by viewModel<TaskViewModel>()
    private lateinit var binding: ActivityFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_form)
        binding.lifecycleOwner = this
        val task = intent.getParcelableExtra(Constants.TASK) ?: Task()
        initView(task)
    }

    private fun showErrorMessage(field: Int) {
        tiDescription.error = when(field){
            Constants.DESCRIPTION_EMPTY -> getString(R.string.required)
            Constants.LETTER_DIGIT_ONLY -> getString(R.string.letter_digit_only)
            else -> ""
        }
    }

    private fun initView(task: Task){
        edDescription.setText(task.description)
        swDone.isChecked = task.done

        var title = getString(R.string.add)
        var iconButton = R.drawable.ic_done

        if(task.id > 0) {
            title = getString(R.string.edit)
            iconButton = R.drawable.ic_refresh
            tbTaskDone.visibility = View.VISIBLE
        }
        else {
            tbTaskDone.visibility = View.GONE
        }

        mainToolbar.title = title
        setSupportActionBar(mainToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fbSave.setImageResource(iconButton)
        edDescription.requestFocus()

        swDone.setOnClickListener {
            val msg = if(swDone.isChecked) R.string.done_task else R.string.pending_task
            toast(this@FormActivity, msg)
        }

        fbSave.setOnClickListener{ onSave(task) }
    }

    private fun onNext(){
        edDescription.setText("")
        edDescription.requestFocus()
    }

    private fun onSave(task: Task){
        task.description = edDescription.text.toString()
        task.done = swDone.isChecked
        viewModel.save(task)

        viewModel.valid.observe(this, Observer{
            tiDescription.isErrorEnabled = !it
            if(it){
                toast(this, R.string.success_saved)
                if(task.id > 0){
                    finish()
                }
                else{
                    onNext()
                }
            }
        })

        viewModel.errorMessage.observe(this, Observer{ showErrorMessage(it) })
    }
}