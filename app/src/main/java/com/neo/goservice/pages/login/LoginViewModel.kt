package com.neo.goservice.pages.login

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.google.gson.Gson
import com.neo.goservice.repository.Repository
import com.neo.goservice.repository.data.Login
import com.neo.goservice.repository.data.LoginVerify
import com.neo.goservice.repository.data.ReLogin
import com.neo.goservice.repository.viewModel.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class LoginViewModel(
        application: Application,
        private val compositeDisposable: CompositeDisposable,
        private val repository: Repository
) : BaseViewModel(application, compositeDisposable) {

    val TAG_LOGIN = 0
    val TAG_FORGET_PASSWORD = 1
    val TAG_TOKEN_VERIFY = 2
    val TAG_RELOGIN = 3

    val loginSuccess = MutableLiveData<Pair<Int, Login>>()
    val loginProgress = MutableLiveData<Pair<Int, Boolean>>()
    val loginError = MutableLiveData<Pair<Int, Throwable>>()

    val reLoginSuccess = MutableLiveData<Pair<Int, ReLogin>>()
    val reLoginProgress = MutableLiveData<Pair<Int, Boolean>>()
    val reLoginError = MutableLiveData<Pair<Int, Throwable>>()

    val tokenVerifySuccess = MutableLiveData<Pair<Int, LoginVerify>>()
    val tokenVerifyProgress = MutableLiveData<Pair<Int, Boolean>>()
    val tokenVerifyError = MutableLiveData<Pair<Int, Throwable>>()

    val mGson = Gson()

    companion object {
        val TAG = LoginViewModel::class.java.simpleName
    }

    init {

    }

    fun login(email: String, password: String) {
        compositeDisposable.add(repository.login(email, password)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { loginProgress.value = Pair(TAG_LOGIN, true) }
                .doFinally { loginProgress.value = Pair(TAG_LOGIN, false) }
                .subscribe({
                    val result = it.string()
                    //查看xml 字串擷取json result
                    val filterResult = result.substring(result.indexOf("return") + 7, result.lastIndexOf("}") + 1)
                    Log.d(TAG, "success filter result= $filterResult")

                    try {
                        val login = mGson.fromJson(filterResult, Login::class.java)
                        loginSuccess.value = Pair(TAG_LOGIN, login)
                    } catch (e: com.google.gson.JsonSyntaxException) {
                        Log.e(TAG, "Error = $e")
                        loginError.value = Pair(TAG_LOGIN, e)
                    }
                }, {
                    Log.e(TAG, "Error = $it")
                    loginError.value = Pair(TAG_LOGIN, it)
                })
        )
    }

    fun reLogin(email: String, password: String, companyId: String = "") {
        compositeDisposable.add(repository.login(email, password, companyId)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { reLoginProgress.value = Pair(TAG_LOGIN, true) }
                .doFinally { reLoginProgress.value = Pair(TAG_LOGIN, false) }
                .subscribe({
                    val result = it.string()
                    //查看xml 字串擷取json result
                    val filterResult = result.substring(result.indexOf("return") + 7, result.lastIndexOf("}") + 1)
                    Log.d(TAG, "success filter result= $filterResult")

                    try {
                        val login = mGson.fromJson(filterResult, ReLogin::class.java)
                        reLoginSuccess.value = Pair(TAG_RELOGIN, login)

                    } catch (e: com.google.gson.JsonSyntaxException) {
                        Log.e(TAG, "Error = $e")
                        reLoginError.value = Pair(TAG_RELOGIN, e)
                    }
                }, {
                    Log.e(TAG, "Error = $it")
                    reLoginError.value = Pair(TAG_RELOGIN, it)
                })
        )
    }

    fun saveToken(token: String) {
        repository.saveToken(token)
    }

    fun checkToken() {
        compositeDisposable.add(repository.ssoLogin()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { tokenVerifyProgress.value = Pair(TAG_LOGIN, true) }
                .doFinally { tokenVerifyProgress.value = Pair(TAG_LOGIN, false) }
                .subscribe({
                    val result = it.string()
                    //查看xml 字串擷取json result
                    val filterResult = result.substring(result.indexOf("return") + 7, result.lastIndexOf("}") + 1)
                    Log.d(TAG, "success filter result= $filterResult")

                    try {
                        val login = mGson.fromJson(filterResult, LoginVerify::class.java)
                        tokenVerifySuccess.value = Pair(TAG_TOKEN_VERIFY, login)

                    } catch (e: com.google.gson.JsonSyntaxException) {
                        Log.e(TAG, "Error = $e")
                        tokenVerifyError.value = Pair(TAG_TOKEN_VERIFY, e)
                    }
                }, {
                    Log.e(TAG, "Error = $it")
                    tokenVerifyError.value = Pair(TAG_TOKEN_VERIFY, it)
                })
        )
    }
}