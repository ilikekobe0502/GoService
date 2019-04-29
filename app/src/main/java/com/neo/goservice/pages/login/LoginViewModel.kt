package com.neo.goservice.pages.login

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.neo.goservice.repository.Repository
import com.neo.goservice.repository.viewModel.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class LoginViewModel(
    application: Application,
    private val compositeDisposable: CompositeDisposable,
    private val repository: Repository
) : BaseViewModel(application, compositeDisposable) {


}