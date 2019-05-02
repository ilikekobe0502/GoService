package com.neo.goservice.utils

import android.content.Context
import android.net.wifi.WifiManager
import android.util.Log
import com.google.gson.GsonBuilder
import android.view.inputmethod.InputMethodManager.SHOW_IMPLICIT
import android.support.v4.content.ContextCompat.getSystemService
import android.view.inputmethod.InputMethodManager
import android.widget.EditText


object MiscUtils {

    private val TAG = MiscUtils::class.simpleName

    fun toJSONString(obj: Any?): String {
        var result = ""

        if (obj == null)
            return result
        else {
            try {
                result = GsonBuilder().create().toJson(obj)
            } catch (e: Exception) {
                Log.e(TAG, "Fail to serialize object!", e);
            }
        }
        return result
    }

    inline fun <reified T> parseJSONList(jsonArray: String?): ArrayList<T> {
        if (!jsonArray.isNullOrEmpty())
            return GsonBuilder().create().fromJson(jsonArray, ArrayList<T>()::class.java)
        else
            return ArrayList()
    }

    fun checkContextIsNull(context: Context?): Boolean {
        return context == null
    }

    /**
     * 顯示軟鍵盤
     * @context
     * @view: EditText View
     */
    fun showSoftInput(context: Context?, view: EditText) {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm!!.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }

    /**
     * Get IP address
     * @context
     *
     * @return IP address
     */
    fun getIpAddress(context: Context): String {
        val ip: Int = (context.getSystemService(Context.WIFI_SERVICE) as WifiManager).connectionInfo.ipAddress

        return ip.toString()
    }
}