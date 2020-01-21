package com.codeblin.darch.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

abstract class BaseFragment<TViewModel : BaseViewModel> : Fragment() {

    abstract val viewModel: TViewModel

    abstract fun getLayout(): Int
    abstract fun initialization()
    abstract fun render(data: ViewState)

    open fun observeEvents(){}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayout(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialization()
        viewModel.observe(this, Observer { data ->
            render(data)
        })
    }

    override fun onResume() {
        super.onResume()
        observeEvents()
    }
}