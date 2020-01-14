package com.codeblin.darch.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codeblin.darch.managers.AdapterDelegateManager
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

abstract class BaseFirebaseAdapter(options: FirebaseRecyclerOptions<BaseModel?>) : FirebaseRecyclerAdapter<BaseModel, BaseViewHolder>(options) {

    protected val delegateManager = AdapterDelegateManager()

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int, data: BaseModel) {
        delegateManager.bindDelegate(data, holder)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        delegateManager.createDelegate(parent, viewType)

    override fun getItemViewType(position: Int): Int = delegateManager.getViewType(getItem(position))

    fun registerAdapterDataObserver(
        onChanged: ((Unit) -> Unit)? = null,
        onItemRangeRemoved: ((Int, Int) -> Unit)? = null,
        onItemRangeMoved: ((Int, Int, Int) -> Unit)? = null,
        onItemRangeInserted: ((Int, Int) -> Unit)? = null,
        onItemRangeChanged: ((Int, Int) -> Unit)? = null,
        onItemRangeChangedWithPayload: ((Int, Int, Any?) -> Unit)? = null
    ){
        registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver(){
            override fun onChanged() {
                super.onChanged()
                onChanged?.invoke(Unit)
            }

            override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
                super.onItemRangeRemoved(positionStart, itemCount)
                onItemRangeRemoved?.invoke(positionStart, itemCount)
            }

            override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
                super.onItemRangeMoved(fromPosition, toPosition, itemCount)
                onItemRangeMoved?.invoke(fromPosition, toPosition, itemCount)
            }

            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                super.onItemRangeInserted(positionStart, itemCount)
                onItemRangeInserted?.invoke(positionStart, itemCount)
            }

            override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
                super.onItemRangeChanged(positionStart, itemCount)
                onItemRangeChanged?.invoke(positionStart, itemCount)
            }

            override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {
                super.onItemRangeChanged(positionStart, itemCount, payload)
                onItemRangeChangedWithPayload?.invoke(positionStart, itemCount, payload)
            }
        })
    }
}