package com.neo.goservice

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.neo.goservice.pages.TemplateFragment
import com.neo.goservice.pages.TemplateViewModel
import com.neo.goservice.pages.evaluate.PriceEvaluateViewModel
import com.neo.goservice.pages.login.LoginViewModel
import com.neo.goservice.pages.notification.NotificationViewModel
import com.neo.goservice.pages.status.StatusViewModel
import com.neo.goservice.repository.Repository
import com.neo.goservice.repository.provider.preferences.SharedPreferencesProvider
import com.neo.goservice.repository.provider.resource.ResourceProvider
import io.reactivex.disposables.CompositeDisposable


class ViewModelFactory(private val application: Application,
                       private val repository: Repository,
                       private val preferences: SharedPreferencesProvider,
                       private val resource: ResourceProvider
) : ViewModelProvider.NewInstanceFactory() {

    //ViewModel需要的model再打進去

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return with(modelClass) {
            when {
                isAssignableFrom(TemplateFragment::class.java) -> TemplateViewModel(application, CompositeDisposable(), repository)
                isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(application, CompositeDisposable(), repository)
                isAssignableFrom(StatusViewModel::class.java) -> StatusViewModel(application, CompositeDisposable(), repository)
                isAssignableFrom(NotificationViewModel::class.java) -> NotificationViewModel(application, CompositeDisposable(), repository)
                isAssignableFrom(PriceEvaluateViewModel::class.java) -> PriceEvaluateViewModel(application, CompositeDisposable(), repository)
                else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            } as T
        }
    }
}