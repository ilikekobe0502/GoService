package com.neo.goservice.pages.login

import android.content.Context
import com.neo.goservice.R

enum class LanguageEnum(title: Int, code: String) {
    TW(R.string.common_lang_tw, "zh-rTW"),
    EN(R.string.common_lang_en, "zh-en-rUS");

    companion object {
        fun getTitle(context: Context): List<String> {
            val titles: ArrayList<String> = ArrayList<String>()
            for (item in values()) {
                titles.add(context.getString(item.mTitle))
            }
            return titles
        }
    }

    val mTitle: Int = title
    val mCode: String = code
}