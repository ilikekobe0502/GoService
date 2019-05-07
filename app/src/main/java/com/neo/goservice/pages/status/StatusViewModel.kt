package com.neo.goservice.pages.status

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.neo.goservice.repository.Repository
import com.neo.goservice.repository.data.Status
import com.neo.goservice.repository.viewModel.BaseViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class StatusViewModel(
        application: Application,
        private val compositeDisposable: CompositeDisposable,
        private val repository: Repository
) : BaseViewModel(application, compositeDisposable) {

    val getStatusSuccess = MutableLiveData<Status>()
    val getStatusProgress = MutableLiveData<Boolean>()
    val getStatusError = MutableLiveData<Throwable>()

    val s: String = "{\"facilities\":[{\"title\":\"A區\",\"machines\":[{\"name\":\"3廠1號機\"},{\"name\":\"3廠12號機\"},{\"name\":\"3廠11號機\"}]},{\"title\":\"B區\",\"machines\":[{\"name\":\"5廠1號機\"},{\"name\":\"5廠12號機\"},{\"name\":\"5廠11號機\"},{\"name\":\"52廠1號機\"}]},{\"title\":\"C區\",\"machines\":[{\"name\":\"6廠1號機\"},{\"name\":\"6廠2號機\"}]}]}"

    val mGson = Gson()

    companion object {
        val TAG = StatusViewModel::class.java.simpleName
    }

    init {
        compositeDisposable.add(Single.just(mGson.fromJson(s, Status::class.java))
                .doOnSubscribe { getStatusProgress.value = true }
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally { getStatusProgress.value = false }
                .subscribe({
                    getStatusSuccess.value = it
                }, {
                    getStatusError.value = it
                })
        )

    }
}