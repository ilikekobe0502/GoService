package com.neo.goservice.pages.base

import android.os.Bundle
import android.support.annotation.IdRes
import com.neo.goservice.helps.NoticeDialogHelper

interface OnPageInteractionListener {

    interface Base {
        fun pressBack()
        fun showFullScreenLoading()
        fun hideFullScreenOverlay()
        fun showNoticeDialog(message: String, type: Int = NoticeDialogHelper.TYPE_DEFAULT)
    }

    interface Pane : Base {
        fun switchPage(@IdRes container: Int, page: Int, args: Bundle, addToBackStack: Boolean, withAnimation: Boolean)
        fun addPage(@IdRes container: Int, page: Int, args: Bundle, addToBackStack: Boolean, withAnimation: Boolean)
    }

    interface Primary : Pane
}