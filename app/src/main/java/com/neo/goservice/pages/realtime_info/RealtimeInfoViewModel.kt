package com.neo.goservice.pages.realtime_info

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.google.gson.Gson
import com.neo.goservice.repository.Repository
import com.neo.goservice.repository.data.FacilitiesInfo
import com.neo.goservice.repository.viewModel.BaseViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RealtimeInfoViewModel(
        application: Application,
        private val compositeDisposable: CompositeDisposable,
        private val repository: Repository
) : BaseViewModel(application, compositeDisposable) {

    val getRealtimgInfoSuccess = MutableLiveData<FacilitiesInfo>()
    val getRealtimgInfoProgress = MutableLiveData<Boolean>()
    val getRealtimgInfoError = MutableLiveData<Throwable>()

    val s: String = "{\"facilities\":{\"air_compressor\":[{\"title\":\"TW185852\",\"description\":\"鋼鐵人，本名安東尼·愛德華·史塔克，暱稱東尼·史塔克，是一位在漫威漫畫世界中的虛構漫畫超級英雄，第一代。鋼鐵人由史丹·李，拉里·李伯，唐·赫克與傑克·科比共同創造。鋼鐵人首次出現於《懸疑故事》第39期，臺灣老三台於1970年代曾以《萬能金鋼》之名播出此作的改編卡通。最大敵人為滿大人。\",\"effective_time\":\"2018-05-24\",\"warranty_time_start\":\"2018-05-24\",\"warranty_time_end\":\"2018-05-24\",\"model\":\"SA37PLUS\",\"area\":\"3廠2F\",\"name\":\"SC 3F#3機-定頻(AC23)\",\"engineer\":\"Alex Yeh\"},{\"title\":\"停機公告\",\"description\":\"鋼鐵人，本名安東尼·愛德華·史塔克，暱稱東尼·史塔克，是一位在漫威漫畫世界中的虛構漫畫超級英雄，第一代。鋼鐵人由史丹·李，拉里·李伯，唐·赫克與傑克·科比共同創造。鋼鐵人首次出現於《懸疑故事》第39期，臺灣老三台於1970年代曾以《萬能金鋼》之名播出此作的改編卡通。最大敵人為滿大人。\",\"effective_time\":\"2018-05-24\",\"warranty_time_start\":\"2018-05-24\",\"warranty_time_end\":\"2018-05-24\",\"model\":\"SA37PLUS\",\"area\":\"3廠2F\",\"name\":\"SC 3F#3機-定頻(AC23)\",\"engineer\":\"Alex Yeh\"},{\"title\":\"停機公告\",\"description\":\"鋼鐵人，本名安東尼·愛德華·史塔克，暱稱東尼·史塔克，是一位在漫威漫畫世界中的虛構漫畫超級英雄，第一代。鋼鐵人由史丹·李，拉里·李伯，唐·赫克與傑克·科比共同創造。鋼鐵人首次出現於《懸疑故事》第39期，臺灣老三台於1970年代曾以《萬能金鋼》之名播出此作的改編卡通。最大敵人為滿大人。\",\"effective_time\":\"2018-05-24\",\"warranty_time_start\":\"2018-05-24\",\"warranty_time_end\":\"2018-05-24\",\"model\":\"SA37PLUS\",\"area\":\"3廠2F\",\"name\":\"SC 3F#3機-定頻(AC23)\",\"engineer\":\"Alex Yeh\"},{\"title\":\"停機公告\",\"description\":\"鋼鐵人，本名安東尼·愛德華·史塔克，暱稱東尼·史塔克，是一位在漫威漫畫世界中的虛構漫畫超級英雄，第一代。鋼鐵人由史丹·李，拉里·李伯，唐·赫克與傑克·科比共同創造。鋼鐵人首次出現於《懸疑故事》第39期，臺灣老三台於1970年代曾以《萬能金鋼》之名播出此作的改編卡通。最大敵人為滿大人。\",\"effective_time\":\"2018-05-24\",\"warranty_time_start\":\"2018-05-24\",\"warranty_time_end\":\"2018-05-24\",\"model\":\"SA37PLUS\",\"area\":\"3廠2F\",\"name\":\"SC 3F#3機-定頻(AC23)\",\"engineer\":\"Alex Yeh\"},{\"title\":\"停機公告\",\"description\":\"鋼鐵人，本名安東尼·愛德華·史塔克，暱稱東尼·史塔克，是一位在漫威漫畫世界中的虛構漫畫超級英雄，第一代。鋼鐵人由史丹·李，拉里·李伯，唐·赫克與傑克·科比共同創造。鋼鐵人首次出現於《懸疑故事》第39期，臺灣老三台於1970年代曾以《萬能金鋼》之名播出此作的改編卡通。最大敵人為滿大人。\",\"effective_time\":\"2018-05-24\",\"warranty_time_start\":\"2018-05-24\",\"warranty_time_end\":\"2018-05-24\",\"model\":\"SA37PLUS\",\"area\":\"3廠2F\",\"name\":\"SC 3F#3機-定頻(AC23)\",\"engineer\":\"Alex Yeh\"},{\"title\":\"停機公告\",\"description\":\"鋼鐵人，本名安東尼·愛德華·史塔克，暱稱東尼·史塔克，是一位在漫威漫畫世界中的虛構漫畫超級英雄，第一代。鋼鐵人由史丹·李，拉里·李伯，唐·赫克與傑克·科比共同創造。鋼鐵人首次出現於《懸疑故事》第39期，臺灣老三台於1970年代曾以《萬能金鋼》之名播出此作的改編卡通。最大敵人為滿大人。\",\"effective_time\":\"2018-05-24\",\"warranty_time_start\":\"2018-05-24\",\"warranty_time_end\":\"2018-05-24\",\"model\":\"SA37PLUS\",\"area\":\"3廠2F\",\"name\":\"SC 3F#3機-定頻(AC23)\",\"engineer\":\"Alex Yeh\"}],\"accessory\":{}}}"

    val mGson = Gson()

    companion object {
        val TAG = RealtimeInfoViewModel::class.java.simpleName
    }

    init {
        compositeDisposable.add(Single.fromCallable { mGson.fromJson(s, FacilitiesInfo::class.java) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { getRealtimgInfoProgress.value = true }
                .doFinally { getRealtimgInfoProgress.value = false }
                .subscribe({
                    getRealtimgInfoSuccess.value = it

                }, {
                    getRealtimgInfoError.value = it
                    Log.e(TAG, "error = $it")
                })
        )
    }
}