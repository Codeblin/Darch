package com.codeblin.darch.base

import android.app.Application
import androidx.lifecycle.*

abstract class BaseViewModel: ViewModel(){
    protected val dataStream = MutableLiveData<ViewState>()
    protected var viewStates: ViewState = Loading
        set(value) {
            field = value
            dataStream.postValue(field)
        }

    /** Observe the data stream forever. Mostly for test cases */
    fun observeForever(owner: LifecycleOwner, observer: Observer<ViewState>){
        dataStream.observe(owner, observer)
    }

    fun observe(owner: LifecycleOwner, observer: Observer<ViewState>){
        dataStream.observe(owner, observer)
    }

    fun stopObserve(owner: LifecycleOwner){
        dataStream.removeObservers(owner)
    }

    open fun postData(){
        dataStream.postValue(viewStates)
    }

    open fun postLoading(){
        dataStream.postValue(Loading)
    }
}