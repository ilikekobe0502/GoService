package com.neo.goservice.helps

import android.app.AlertDialog
import android.content.Context
import com.neo.goservice.R

class NoticeDialogHelper(val mContext: Context) {
    companion object {
        val TYPE_DEFAULT: Int = 0
        val TYPE_ERROR: Int = 1
        fun getInstance(context: Context): NoticeDialogHelper {
            return NoticeDialogHelper(context)
        }
    }

    fun show(message: String, type: Int = TYPE_DEFAULT) {
        AlertDialog.Builder(mContext)
                .setTitle(checkType(type))
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(R.string.common_ok,null)
                .show()
    }

    private fun checkType(type: Int): String {
        var s: String = ""
        when (type) {
            TYPE_DEFAULT -> {
                s = "Message : "
            }
            TYPE_ERROR -> {
                s = "Error : "
            }
        }
        return s
    }
}