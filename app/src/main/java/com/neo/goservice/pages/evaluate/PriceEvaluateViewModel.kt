package com.neo.goservice.pages.evaluate

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.google.gson.Gson
import com.neo.goservice.repository.Repository
import com.neo.goservice.repository.data.PriceEvaluate
import com.neo.goservice.repository.viewModel.BaseViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class PriceEvaluateViewModel(
        application: Application,
        private val compositeDisposable: CompositeDisposable,
        private val repository: Repository
) : BaseViewModel(application, compositeDisposable) {
    private val TAG = PriceEvaluateViewModel::class.java.simpleName

    val getEvaluateSuccess = MutableLiveData<PriceEvaluate>()
    val getEvaluateProgress = MutableLiveData<Boolean>()
    val getEvaluateError = MutableLiveData<Throwable>()

    val s = "{\"price_evaluate\":{\"machine\":[{\"name\":\"機器1\",\"car_empty\":\"10h\",\"car_full\":\"20h\"},{\"name\":\"機器2\",\"car_empty\":\"5h\",\"car_full\":\"30h\"},{\"name\":\"機器3\",\"car_empty\":\"6h\",\"car_full\":\"10h\"},{\"name\":\"機器4\",\"car_empty\":\"10h\",\"car_full\":\"30h\"}],\"total_info\":{\"price\":\"44000\",\"car_empty\":\"80\",\"car_full\":\"20\"},\"car_full_info\":{\"hour\":\"300\",\"whtt\":\"10000\",\"price\":\"30000\"}}}"
    val mGson = Gson()

    init {
        compositeDisposable.add(Single.just(mGson.fromJson(s, PriceEvaluate::class.java))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { getEvaluateProgress.value = true }
                .doFinally { getEvaluateProgress.value = false }
                .subscribe({
                    getEvaluateSuccess.value = it

                }, {
                    getEvaluateError.value = it
                    Log.e(TAG, "error = $it")
                })
        )
    }

}