package br.com.monisoftware.taskmanager.data.dao

import androidx.lifecycle.*

class OneTimeObserver<T>(private val handler: (T) -> Unit) : Observer<T>, LifecycleOwner{
    private val lifecycle = LifecycleRegistry(this)

    init{
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
    }

    override fun getLifecycle() = lifecycle

    override fun onChanged(t: T) {
        handler(t)
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    }
}