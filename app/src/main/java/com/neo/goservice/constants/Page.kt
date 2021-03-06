package com.neo.goservice.constants

import android.os.Bundle
import android.support.v4.app.Fragment
import com.neo.goservice.pages.home.HomeFragment
import com.neo.goservice.pages.TemplateFragment
import com.neo.goservice.pages.facilities_info.FacilitiesInfoFragment
import com.neo.goservice.pages.evaluate.PriceEvaluateFragment
import com.neo.goservice.pages.login.LoginFragment
import com.neo.goservice.pages.notification.NotificationFragment
import com.neo.goservice.pages.realtime_info.RealtimeInfoFragment
import com.neo.goservice.pages.status.StatusFragment
import java.lang.IllegalArgumentException

object Page {

    const val PAGE = "page"
    const val ARG_PAGE = "com.neo.goservice.constants.Page.ARG_PAGE"
    const val ARG_DATA = "com.neo.goservice.constants.Page.ARG_DATA"

    const val INVALID_PAGE = -1

    const val TEMPLATE = 1000
    const val HOME = 1001
    const val LOGIN = 1002
    const val STATUS = 1003
    const val NOTIFICATION = 1004
    const val FACILITIES_INFO = 1005
    const val PRICE_EVALUATE = 1006
    const val REALTIME_INFO = 1007

    /*--------------------------------------------------------------------------------------------*/
    /* Helpers */
    fun tag(page: Int): String = "page_$page"

    fun view(page: Int, args: Bundle): Fragment {
        var result: Fragment

        when (page) {
            TEMPLATE -> result = TemplateFragment.newInstance()
            HOME -> result = HomeFragment.newInstance()
            LOGIN -> result = LoginFragment.newInstance()
            STATUS -> result = StatusFragment.newInstance()
            NOTIFICATION -> result = NotificationFragment.newInstance()
            FACILITIES_INFO -> result = FacilitiesInfoFragment.newInstance()
            PRICE_EVALUATE -> result = PriceEvaluateFragment.newInstance()
            REALTIME_INFO -> result = RealtimeInfoFragment.newInstance()
            else -> throw IllegalArgumentException("No match view! page = $page")
        }

        args.putInt(ARG_PAGE, page)
        putData(result, args)

        return result
    }

    private fun putData(fragment: Fragment, data: Bundle) {
        var args = fragment.arguments;
        if (args == null) {
            fragment.arguments = data
        } else {
            args.putAll(data)
        }
    }
}