package com.codeblin.darch.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Codeblin S. on 9/18/2019.
 */
abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    abstract fun bindViews(data: BaseModel)
}