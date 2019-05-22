package com.neo.goservice.pages.evaluate

import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.neo.goservice.R
import kotlinx.android.synthetic.main.fragment_price_evaluate.*

class InfoViewPagerAdapter : PagerAdapter() {
    val pageList = ArrayList<View>()
    override fun isViewFromObject(p0: View, p1: Any): Boolean {


        return p1 == p0
    }

    override fun getCount(): Int {
        return 3
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view: View = LayoutInflater.from(container.context).inflate(R.layout.item_status, container, false)
        pageList.add(view)
        return super.instantiateItem(container, position)
    }
}