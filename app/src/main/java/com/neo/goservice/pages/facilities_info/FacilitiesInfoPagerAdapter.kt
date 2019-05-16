package com.neo.goservice.pages.facilities_info

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.ViewGroup
import com.neo.goservice.R
import com.neo.goservice.repository.data.FacilitiesContent

const val TAG_ARG = "com.neo.goservice.pages.facilities_info.arg"

class FacilitiesInfoPagerAdapter(val mContent: Context, val mFm: FragmentManager) : FragmentPagerAdapter(mFm) {
    private val mViewList: ArrayList<Fragment> = ArrayList()
    private var mBundle: Bundle = Bundle()

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

    override fun getPageTitle(position: Int): CharSequence? {
        return mContent.resources.getStringArray(R.array.facilities_info_tab_title_array)[position]
    }

    fun setData(data: FacilitiesContent?) {
        //TODO 需要再根據實際情況修改
        val fragment1 = ContentFragment()
        val b1 = Bundle()
        b1.putSerializable(TAG_ARG, data?.air_compressor)
        fragment1.arguments = b1
        mViewList.add(fragment1)
        mViewList.add(ContentFragment())
        notifyDataSetChanged()
    }
}
