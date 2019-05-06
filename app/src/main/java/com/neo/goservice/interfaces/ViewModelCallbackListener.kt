package com.neo.goservice.interfaces

interface ViewModelCallbackListener {
    fun onSuccess(it: Any? = null)
    fun onError(t: Throwable? = null)
    fun onProgress(b: Boolean? = false)
}