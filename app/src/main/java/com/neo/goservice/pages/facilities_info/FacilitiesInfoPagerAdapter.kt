package com.neo.goservice.pages.facilities_info

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.ViewGroup

class FacilitiesInfoPagerAdapter(val mFm: FragmentManager) : FragmentPagerAdapter(mFm) {
    private val mViewList: ArrayList<Fragment> = ArrayList()

    init {
        mViewList.add(ContentFragment())
        mViewList.add(ContentFragment())
    }

    override fun getItem(p0: Int): Fragment {
        return mViewList[p0]
    }

    override fun getCount(): Int {
        return mViewList.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        return super.instantiateItem(container, position)
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
    }

    fun setData() {

    }
}
