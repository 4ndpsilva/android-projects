package br.com.monisoftware.taskmanager.view.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.monisoftware.taskmanager.App
import br.com.monisoftware.taskmanager.R
import br.com.monisoftware.taskmanager.data.entity.Task
import br.com.monisoftware.taskmanager.presenter.FormPresenter
import br.com.monisoftware.taskmanager.presenter.contract.FormContract
import br.com.monisoftware.taskmanager.util.Constants
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.form_activity.*

class FormActivity : AppCompatActivity(), FormContract.View<Task> {
    private lateinit var presenter: FormContract.Presenter<Task>
    private var task = Task()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.form_activity)

        checkMode()

        val dao = App.database.getDAO()
        presenter = FormPresenter(this, dao)

        initView()
    }

    override fun onStart() {
        super.onStart()

        if(task.id > 0){
            presenter.getEntityAndPopulate(task.id)
        }
    }

    override fun setPresenter(presenter: FormContract.Presenter<Task>) {
        this.presenter = presenter
    }

    override fun showErrorMessage(field: Int) {
        if(field == Constants.FIELD_DESCRIPTION){
            tiDescription.error = getString(R.string.required)
        }
        else if(field == Constants.FIELD_LETTER_DIGIT_ONLY){
            tiDescription.error = getString(R.string.letter_digit_only)
        }
    }

    override fun showSuccessMessage() {
        Toast.makeText(this, R.string.success_saved, Toast.LENGTH_SHORT).show()
    }

    override fun clearPreErrors() {
        tiDescription.isErrorEnabled = false
    }

    override fun close() {
        finish()
    }

    private fun initView(){
        setSupportActionBar(toolbarMain)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var iconButton = R.drawable.ic_done

        if(task.id > 0) {
            iconButton = R.drawable.ic_refresh
            tlTaskDone.visibility = View.VISIBLE
        }
        else {
            tlTaskDone.visibility = View.GONE
        }

        fbSave.setImageResource(iconButton)
        edDescription.requestFocus()

        swDone.setOnClickListener {
            val msg = if(swDone.isChecked) R.string.done_task else R.string.pending_task
            Toast.makeText(this@FormActivity, msg, Toast.LENGTH_SHORT).show()
        }

        fbSave.setOnClickListener{ prepareSave() }
    }

    override fun populate(task: Task) {
        this.task = task
        edDescription.setText(task.description)
        swDone.isChecked = task.done
    }

    override fun onNext() {
        edDescription.setText("")
    }

    private fun prepareSave(){
        task.description = edDescription.text.toString()
        task.done = swDone.isChecked

        val valid = presenter.validate(task)

        if(!valid) {
            return
        }

        presenter.save(task)
    }

    private fun checkMode(){
        if(intent.extras != null){
            task.id = intent.getLongExtra(Constants.ID, 0)
        }
    }
}