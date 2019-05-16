package com.neo.goservice.pages.facilities_info

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.*
import com.neo.goservice.AppInjector
import com.neo.goservice.R
import com.neo.goservice.interfaces.ViewModelCallbackListener
import com.neo.goservice.pages.base.InteractionView
import com.neo.goservice.pages.base.OnPageInteractionListener
import com.neo.goservice.repository.data.FacilitiesInfo
import kotlinx.android.synthetic.main.fragment_device_info.*

class FacilitiesInfoFragment : InteractionView<OnPageInteractionListener.Primary>(), View.OnFocusChangeListener,
        View.OnClickListener, ViewModelCallbackListener {
    private lateinit var mViewModel: FacilitiesInfoViewModel

    private var mData: FacilitiesInfo? = null
    private var mAdapter: FacilitiesInfoPagerAdapter? = null

    companion object {
        fun newInstance(): FacilitiesInfoFragment = FacilitiesInfoFragment()
        private val TAG = FacilitiesInfoFragment::class.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = AppInjector.obtainViewModel(this)
        mViewModel.getDeviceInfoError.observe(this, Observer { onError(it) })
        mViewModel.getDeviceInfoSuccess.observe(this, Observer { onSuccess(it) })
        mViewModel.getDeviceInfoProgress.observe(this, Observer { onProgress(it) })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_device_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        renderView()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        when (v?.id) {

        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {

        }
    }

    override fun onSuccess(it: Any?) {
        mData = it as FacilitiesInfo?
        renderData()
    }

    override fun onError(t: Throwable?) {

    }

    override fun onProgress(b: Boolean?) {

    }

    private fun renderView() {
        mAdapter = context?.let { fragmentManager?.let { it1 -> FacilitiesInfoPagerAdapter(it, it1) } }
        tab_layout.setupWithViewPager(viewPager)
        viewPager.adapter = mAdapter
    }

    private fun renderData() {
        mAdapter?.setData(mData?.facilities)
    }
}