package com.codeblin.darch.base

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.codeblin.darch.managers.AdapterDelegateManager

abstract class BaseAdapter : ListAdapter<BaseModel, BaseViewHolder>(
    AsyncDifferConfig.Builder(
        BaseDiffCallbacks()
    ).build()
) {

    protected val delegateManager = AdapterDelegateManager()

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) =
        delegateManager.bindDelegate(getItem(position), holder)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        delegateManager.createDelegate(parent, viewType)

    override fun getItemViewType(position: Int): Int = delegateManager.getViewType(getItem(position))
}

class BaseDiffCallbacks : DiffUtil.ItemCallback<BaseModel>() {
    override fun areItemsTheSame(oldItem: BaseModel, newItem: BaseModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: BaseModel, newItem: BaseModel): Boolean {
        return oldItem.contentsAreSame(newItem)
    }
}