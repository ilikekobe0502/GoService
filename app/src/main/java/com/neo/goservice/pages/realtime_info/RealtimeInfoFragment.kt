package com.neo.goservice.pages.realtime_info

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.*
import com.neo.goservice.AppInjector
import com.neo.goservice.R
import com.neo.goservice.interfaces.ViewModelCallbackListener
import com.neo.goservice.pages.base.InteractionView
import com.neo.goservice.pages.base.OnPageInteractionListener
import com.neo.goservice.repository.data.FacilitiesInfo
import kotlinx.android.synthetic.main.view_tool_bar_title.*

class RealtimeInfoFragment : InteractionView<OnPageInteractionListener.Primary>(), View.OnFocusChangeListener,
        View.OnClickListener, ViewModelCallbackListener {
    private lateinit var mViewModel: RealtimeInfoViewModel

    private var mData: FacilitiesInfo? = null
    private var mRecyclerViewAdapter: RealtimeInfoRecyclerViewAdapter? = null

    companion object {
        fun newInstance(): RealtimeInfoFragment = RealtimeInfoFragment()
        private val TAG = RealtimeInfoFragment::class.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = AppInjector.obtainViewModel(this)
        mViewModel.getRealtimgInfoError.observe(this, Observer { onError(it) })
        mViewModel.getRealtimgInfoSuccess.observe(this, Observer { onSuccess(it) })
        mViewModel.getRealtimgInfoProgress.observe(this, Observer { onProgress(it) })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_realtime_info, container, false)
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
        textView_title.text = context?.getText(R.string.home_realtime_info)


    }

    private fun renderData() {
    }
}