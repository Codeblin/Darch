package com.codeblin.app

import android.os.Handler
import com.codeblin.darch.base.*

data class Message(val text: String) : BaseModel()

data class User(val name: String): BaseModel()

class MainViewModel : BaseViewModel(){
    fun fetchData(){
        postLoading()
        Handler().postDelayed({
            viewStates = HasData(Message("All Good!"))
        }, 2000)
    }

    fun fetchNoData(){
        postLoading()
        Handler().postDelayed({
            viewStates = NoData
        }, 2000)
    }

    fun fetchListData(){
        postLoading()
        Handler().postDelayed({
            viewStates = HasListData(listOf(
                User("Sam1"),
                User("Sam1"),
                User("Sam1"),
                User("Sam1"),
                User("Sam1"),
                User("Sam1"),
                User("Sam1"),
                User("Sam1")
            ))
        }, 2000)
    }
}