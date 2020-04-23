package br.com.tasklist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.tasklist.repository.data.entity.Task
import br.com.tasklist.repository.TaskRepository
import br.com.tasklist.util.Constants
import br.com.tasklist.util.StringUtil
import kotlinx.coroutines.launch

class TaskViewModel(private var repository: TaskRepository) : ViewModel() {
    val tasks = repository.findAll()
    val remaining = repository.getRemainingTasks(false)

    private val _valid = MutableLiveData<Boolean>()
    val valid: LiveData<Boolean>
        get() = _valid

    private val _errorMessage = MutableLiveData<Int>()
    val errorMessage: LiveData<Int>
        get() = _errorMessage

    private fun validate(task: Task) {
        if(task.description.isEmpty()){
            _errorMessage.value = Constants.DESCRIPTION_EMPTY
            _valid.value = false
        }
        else if(!StringUtil.isLetterOrDigitOnly(task.description)){
            _errorMessage.value = Constants.LETTER_DIGIT_ONLY
            _valid.value = false
        }
        else{
            _errorMessage.value = 0
            _valid.value = true
        }
    }

    fun save(task: Task) {
        viewModelScope.launch {
            validate(task)

            if (_valid.value!!) {
                if (task.id > 0) {
                    repository.update(task)
                }
                else {
                    repository.save(task)
                }
            }
        }
    }

    fun delete(task: Task? = null) {
        viewModelScope.launch {
            if (task != null) {
                repository.delete(task)
            }
            else {
                repository.delete()
            }
        }
    }

    fun update(task: Task? = null) {
        viewModelScope.launch {
            if (task != null) {
                repository.update(task)
            }
            else {
                repository.updateAllTasks()
            }
        }
    }
}