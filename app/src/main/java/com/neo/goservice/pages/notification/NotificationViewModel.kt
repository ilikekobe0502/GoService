package com.neo.goservice.pages.notification

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.google.gson.Gson
import com.neo.goservice.repository.Repository
import com.neo.goservice.repository.data.Notifications
import com.neo.goservice.repository.viewModel.BaseViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class NotificationViewModel(
        application: Application,
        private val compositeDisposable: CompositeDisposable,
        private val repository: Repository
) : BaseViewModel(application, compositeDisposable) {

    val getNotificationSuccess = MutableLiveData<Notifications>()
    val getNotificationProgress = MutableLiveData<Boolean>()
    val getNotificationError = MutableLiveData<Throwable>()

    val s: String = "{\"notifications\":[{\"time\":\"2018-05-24\",\"message\":\"鋼鐵人，本名安東尼·愛德華·史塔克，暱稱東尼·史塔克，是一位在漫威漫畫世界中的虛構漫畫超級英雄，第一代。鋼鐵人由史丹·李，拉里·李伯，唐·赫克與傑克·科比共同創造。鋼鐵人首次出現於《懸疑故事》第39期，臺灣老三台於1970年代曾以《萬能金鋼》之名播出此作的改編卡通。最大敵人為滿大人。\",\"title\":\"停機公告\",\"read\":0},{\"time\":\"2018-05-24\",\"message\":\"鋼鐵人，本名安東尼·愛德華·史塔克，暱稱東尼·史塔克，是一位在漫威漫畫世界中的虛構漫畫超級英雄，第一代。鋼鐵人由史丹·李，拉里·李伯，唐·赫克與傑克·科比共同創造。鋼鐵人首次出現於《懸疑故事》第39期，臺灣老三台於1970年代曾以《萬能金鋼》之名播出此作的改編卡通。最大敵人為滿大人。\",\"title\":\"停機公告\",\"read\":1},{\"time\":\"2018-05-24\",\"message\":\"鋼鐵人，本名安東尼·愛德華·史塔克，暱稱東尼·史塔克，是一位在漫威漫畫世界中的虛構漫畫超級英雄，第一代。鋼鐵人由史丹·李，拉里·李伯，唐·赫克與傑克·科比共同創造。鋼鐵人首次出現於《懸疑故事》第39期，臺灣老三台於1970年代曾以《萬能金鋼》之名播出此作的改編卡通。最大敵人為滿大人。\",\"title\":\"年度公告\",\"read\":0},{\"time\":\"2018-05-24\",\"message\":\"鋼鐵人，本名安東尼·愛德華·史塔克，暱稱東尼·史塔克，是一位在漫威漫畫世界中的虛構漫畫超級英雄，第一代。鋼鐵人由史丹·李，拉里·李伯，唐·赫克與傑克·科比共同創造。鋼鐵人首次出現於《懸疑故事》第39期，臺灣老三台於1970年代曾以《萬能金鋼》之名播出此作的改編卡通。最大敵人為滿大人。\",\"title\":\"停機公告\",\"read\":0},{\"time\":\"2018-05-24\",\"message\":\"鋼鐵人，本名安東尼·愛德華·史塔克，暱稱東尼·史塔克，是一位在漫威漫畫世界中的虛構漫畫超級英雄，第一代。鋼鐵人由史丹·李，拉里·李伯，唐·赫克與傑克·科比共同創造。鋼鐵人首次出現於《懸疑故事》第39期，臺灣老三台於1970年代曾以《萬能金鋼》之名播出此作的改編卡通。最大敵人為滿大人。\",\"title\":\"年度公告\",\"read\":0},{\"time\":\"2018-05-24\",\"message\":\"鋼鐵人，本名安東尼·愛德華·史塔克，暱稱東尼·史塔克，是一位在漫威漫畫世界中的虛構漫畫超級英雄，第一代。鋼鐵人由史丹·李，拉里·李伯，唐·赫克與傑克·科比共同創造。鋼鐵人首次出現於《懸疑故事》第39期，臺灣老三台於1970年代曾以《萬能金鋼》之名播出此作的改編卡通。最大敵人為滿大人。\",\"title\":\"停機公告\",\"read\":0},{\"time\":\"2018-05-24\",\"message\":\"鋼鐵人，本名安東尼·愛德華·史塔克，暱稱東尼·史塔克，是一位在漫威漫畫世界中的虛構漫畫超級英雄，第一代。鋼鐵人由史丹·李，拉里·李伯，唐·赫克與傑克·科比共同創造。鋼鐵人首次出現於《懸疑故事》第39期，臺灣老三台於1970年代曾以《萬能金鋼》之名播出此作的改編卡通。最大敵人為滿大人。\",\"title\":\"系統公告\",\"read\":1},{\"time\":\"2018-05-24\",\"message\":\"鋼鐵人，本名安東尼·愛德華·史塔克，暱稱東尼·史塔克，是一位在漫威漫畫世界中的虛構漫畫超級英雄，第一代。鋼鐵人由史丹·李，拉里·李伯，唐·赫克與傑克·科比共同創造。鋼鐵人首次出現於《懸疑故事》第39期，臺灣老三台於1970年代曾以《萬能金鋼》之名播出此作的改編卡通。最大敵人為滿大人。\",\"title\":\"系統公告\",\"read\":1},{\"time\":\"2018-05-24\",\"message\":\"鋼鐵人，本名安東尼·愛德華·史塔克，暱稱東尼·史塔克，是一位在漫威漫畫世界中的虛構漫畫超級英雄，第一代。鋼鐵人由史丹·李，拉里·李伯，唐·赫克與傑克·科比共同創造。鋼鐵人首次出現於《懸疑故事》第39期，臺灣老三台於1970年代曾以《萬能金鋼》之名播出此作的改編卡通。最大敵人為滿大人。\",\"title\":\"系統公告\",\"read\":1},{\"time\":\"2018-05-24\",\"message\":\"鋼鐵人，本名安東尼·愛德華·史塔克，暱稱東尼·史塔克，是一位在漫威漫畫世界中的虛構漫畫超級英雄，第一代。鋼鐵人由史丹·李，拉里·李伯，唐·赫克與傑克·科比共同創造。鋼鐵人首次出現於《懸疑故事》第39期，臺灣老三台於1970年代曾以《萬能金鋼》之名播出此作的改編卡通。最大敵人為滿大人。\",\"title\":\"系統公告\",\"read\":1},{\"time\":\"2018-05-24\",\"message\":\"鋼鐵人，本名安東尼·愛德華·史塔克，暱稱東尼·史塔克，是一位在漫威漫畫世界中的虛構漫畫超級英雄，第一代。鋼鐵人由史丹·李，拉里·李伯，唐·赫克與傑克·科比共同創造。鋼鐵人首次出現於《懸疑故事》第39期，臺灣老三台於1970年代曾以《萬能金鋼》之名播出此作的改編卡通。最大敵人為滿大人。\",\"title\":\"系統公告\",\"read\":1},{\"time\":\"2018-05-24\",\"message\":\"鋼鐵人，本名安東尼·愛德華·史塔克，暱稱東尼·史塔克，是一位在漫威漫畫世界中的虛構漫畫超級英雄，第一代。鋼鐵人由史丹·李，拉里·李伯，唐·赫克與傑克·科比共同創造。鋼鐵人首次出現於《懸疑故事》第39期，臺灣老三台於1970年代曾以《萬能金鋼》之名播出此作的改編卡通。最大敵人為滿大人。\",\"title\":\"系統公告\",\"read\":1},{\"time\":\"2018-05-24\",\"message\":\"鋼鐵人，本名安東尼·愛德華·史塔克，暱稱東尼·史塔克，是一位在漫威漫畫世界中的虛構漫畫超級英雄，第一代。鋼鐵人由史丹·李，拉里·李伯，唐·赫克與傑克·科比共同創造。鋼鐵人首次出現於《懸疑故事》第39期，臺灣老三台於1970年代曾以《萬能金鋼》之名播出此作的改編卡通。最大敵人為滿大人。\",\"title\":\"系統公告\",\"read\":1}]}"

    val mGson = Gson()

    companion object {
        val TAG = NotificationViewModel::class.java.simpleName
    }

    init {
        compositeDisposable.add(Single.just(mGson.fromJson(s, Notifications::class.java))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { getNotificationProgress.value = true }
                .doFinally { getNotificationProgress.value = false }
                .subscribe({
                    getNotificationSuccess.value = it

                }, {
                    getNotificationError.value = it
                    Log.e(TAG, "error = $it")
                })
        )
    }
}