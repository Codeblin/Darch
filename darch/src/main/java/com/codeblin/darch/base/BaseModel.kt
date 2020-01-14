package com.codeblin.darch.base

/**
 * Created by Stamatis Stiliatis Togrou on 12/2/2018.
 * @param id is used for DiffUtils
 */
abstract class BaseModel{
    open fun contentsAreSame(newItem: BaseModel): Boolean = true
}