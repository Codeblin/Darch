package com.codeblin.app

import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.codeblin.darch.base.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel>() {

    private val adapter = MainAdapter()
    override val viewModel: MainViewModel by lazy { ViewModelProviders.of(this)[MainViewModel::class.java] }

    override fun getLayout(): Int = R.layout.activity_main

    override fun initialization() {
        rcvData.layoutManager = LinearLayoutManager(this)
        rcvData.adapter = adapter
        viewModel.fetchListData()
    }

    override fun render(viewState: ViewState) {
        when (viewState) {
            Loading -> {
                onLoading()
            }
            NoData, Error() -> {
                onError()
            }
            is HasData<*> -> {
                showMessage((viewState as Message).text)
            }
            is HasListData<*> -> {
                showData(viewState.data as List<User>)
            }
        }
    }

    private fun onLoading() {
        txtLoading.visibility = View.VISIBLE
        txtData.visibility = View.GONE
        txtNoData.visibility = View.GONE
    }

    private fun onError() {
        txtLoading.visibility = View.GONE
        txtData.visibility = View.GONE
        txtNoData.visibility = View.VISIBLE
    }

    private fun showMessage(message: String) {
        txtLoading.visibility = View.GONE
        txtNoData.visibility = View.GONE
        txtData.visibility = View.VISIBLE
        rcvData.visibility = View.GONE
        txtData.text = message
    }

    private fun showData(data: List<User>){
        txtLoading.visibility = View.GONE
        txtNoData.visibility = View.GONE
        txtData.visibility = View.GONE
        rcvData.visibility = View.VISIBLE
        adapter.submitList(data)
    }
}
