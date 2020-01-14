package com.codeblin.darch.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

abstract class BaseViewModel<T: BaseModel>(protected val app: Application): AndroidViewModel(app){
    protected val dataStream = MutableLiveData<ViewState<T>>()
    protected var viewStates: ViewState<T> = ViewState()
        set(value) {
            field = value
            dataStream.postValue(field)
        }

    fun observe(owner: LifecycleOwner, observer: Observer<ViewState<T>>){
        dataStream.observe(owner, observer)
    }

    open fun postData(){
        dataStream.postValue(viewStates)
    }

    open fun postLoading(){
        dataStream.postValue(ViewState(States.LOADING, ViewStateData()))
    }
}

abstract class StateViewModel(protected val app: Application): AndroidViewModel(app){
    protected val stateStream = MutableLiveData<States>()
    protected var state: States = States.FINISHED
        set(value) {
            field = value
            stateStream.postValue(field)
        }

    fun observe(owner: LifecycleOwner, observer: Observer<States>){
        stateStream.observe(owner, observer)
    }
}