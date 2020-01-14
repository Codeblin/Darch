package com.codeblin.darch.base

import android.view.ViewGroup
import com.codeblin.darch.base.BaseModel
import com.codeblin.darch.base.BaseViewHolder

abstract class BaseAdapterDelegate<T : BaseModel> {
    abstract val layoutResource: Int
    abstract fun createViewHolder(parent: ViewGroup, layoutResource: Int): BaseViewHolder
    abstract fun isViewType(data: Any): Boolean
}