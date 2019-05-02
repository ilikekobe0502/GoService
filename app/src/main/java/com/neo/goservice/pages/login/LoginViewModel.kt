package com.neo.goservice.pages.login

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.google.gson.Gson
import com.neo.goservice.repository.Repository
import com.neo.goservice.repository.data.Login
import com.neo.goservice.repository.viewModel.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class LoginViewModel(
        application: Application,
        private val compositeDisposable: CompositeDisposable,
        private val repository: Repository
) : BaseViewModel(application, compositeDisposable) {

    val initLoginSuccess = MutableLiveData<Login>()
    val initLoginProgress = MutableLiveData<Boolean>()
    val initLoginError = MutableLiveData<Throwable>()

    val mGson = Gson()

    companion object {
        val TAG = LoginViewModel::class.java.simpleName
    }

    init {

    }

    fun login(email: String, password: String) {
        compositeDisposable.add(repository.login(email, password)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { initLoginProgress.value = true }
                .doFinally { initLoginProgress.value = false }
                .subscribe({
                    val result = it.string()
                    //查看xml 字串擷取json result
                    val filterResult = result.substring(result.indexOf("return") + 7, result.lastIndexOf("}") + 1)
                    Log.d(TAG, "success filter result= $filterResult")

                    try {
                        initLoginSuccess.value = mGson.fromJson(filterResult, Login::class.java)
                    } catch (e: com.google.gson.JsonSyntaxException) {
                        Log.e(TAG, "error = $e")
                        initLoginError.value = e
                    }
                }, {
                    initLoginError.value = it
                    Log.e(TAG, "error = $it")
                })
        )
    }
}