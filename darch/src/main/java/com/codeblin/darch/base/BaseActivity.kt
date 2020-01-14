package com.codeblin.darch.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

abstract class BaseActivity<TOutput : BaseModel, TViewModel : BaseViewModel<TOutput>> : SimpleActivity() {

    abstract val viewModel: TViewModel

    abstract fun render(viewState: ViewState<TOutput>)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.observe(this, Observer { data ->
            render(data)
        })
    }
}

abstract class SimpleActivity : AppCompatActivity() {

    abstract fun getLayout(): Int
    abstract fun initialization()

    open fun initListeners(){}
    open fun removeListeners(){}
    open fun observeState(){}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        initialization()
        initListeners()
        observeState()
    }

    override fun onDestroy() {
        super.onDestroy()
        removeListeners()
    }
}

