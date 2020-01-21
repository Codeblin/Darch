package com.codeblin.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codeblin.darch.base.BaseAdapter
import com.codeblin.darch.base.BaseAdapterDelegate
import com.codeblin.darch.base.BaseModel
import com.codeblin.darch.base.BaseViewHolder
import kotlinx.android.synthetic.main.row_main.view.*

class MainAdapter: BaseAdapter(){
    init {
        delegateManager.add(MainRowDelegate())
    }
}

class MainRowDelegate : BaseAdapterDelegate(){
    override val layoutResource: Int = R.layout.row_main

    override fun createViewHolder(parent: ViewGroup, layoutResource: Int): BaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutResource, parent, false)
        return MainViewHolder(view)
    }

    override fun isViewType(data: Any): Boolean = data is User

}

class MainViewHolder(itemView: View): BaseViewHolder(itemView){
    override fun bindViews(data: BaseModel) {
        data as User
        itemView.txtData.text = data.name
    }
}