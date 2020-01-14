package com.codeblin.darch.base

import java.lang.Exception

enum class States{
    FINISHED,
    LOADING,
    ERROR
}

data class ViewStateData<T: BaseModel>(val items: List<T> = listOf(), val error: Exception = Exception())

class ViewState<T: BaseModel> {
    val state: States
    val result: ViewStateData<T>

    constructor(state: States, result: ViewStateData<T>){
        this.state = state
        this.result = result
    }

    constructor(){
        state = States.FINISHED
        result = ViewStateData()
    }
}