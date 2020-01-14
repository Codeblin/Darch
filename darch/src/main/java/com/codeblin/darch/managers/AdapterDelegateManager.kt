package com.codeblin.darch.managers

import android.util.Log
import android.view.ViewGroup
import com.codeblin.darch.base.BaseAdapterDelegate
import com.codeblin.darch.base.BaseModel
import com.codeblin.darch.base.BaseViewHolder
import java.lang.ClassCastException
import java.lang.Exception

class AdapterDelegateManager {
    private val delegates = mutableListOf<BaseAdapterDelegate<BaseModel>>()

    fun <T : BaseModel> add(delegate: BaseAdapterDelegate<T>) {
        try {
            val unwrapped = delegate as? BaseAdapterDelegate<BaseModel>
            if (unwrapped != null) {
                delegates.add(unwrapped)
            }
        } catch (ex: ClassCastException) {
            Log.e("AdapterDelegateManager", ex.message)
        }
    }

    fun getViewType(data: BaseModel): Int = delegates.find { it.isViewType(data) }?.layoutResource ?: -1

    fun createDelegate(parent: ViewGroup, viewType: Int): BaseViewHolder =
        delegates.find { it.layoutResource == viewType }?.createViewHolder(parent, viewType)
            ?: throw Exception("Invalid ViewHolder")

    fun bindDelegate(data: BaseModel, holder: BaseViewHolder) = holder.bindViews(data)
}