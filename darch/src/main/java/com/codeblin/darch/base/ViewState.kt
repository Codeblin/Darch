package com.codeblin.darch.base

sealed class ViewState

object Loading: ViewState()

object NoData: ViewState()

class HasListData<out T: BaseModel>(val data: List<T>): ViewState()

class HasData<out T: BaseModel>(val data: T): ViewState()

class Error(val message: String): ViewState()