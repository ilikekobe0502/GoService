package com.neo.goservice.pages.status

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import com.neo.goservice.AppInjector
import com.neo.goservice.R
import com.neo.goservice.interfaces.ViewModelCallbackListener
import com.neo.goservice.pages.base.InteractionView
import com.neo.goservice.pages.base.OnPageInteractionListener
import com.neo.goservice.repository.data.Status
import kotlinx.android.synthetic.main.fragment_status.*

class StatusFragment : InteractionView<OnPageInteractionListener.Primary>(),
        View.OnClickListener, ViewModelCallbackListener {
    private lateinit var mViewModel: StatusViewModel
    private var mStatusAdapter: StatusRecyclerViewAdapter? = null
    private var mFacilitiesAdapter: FacilitiesRecyclerViewAdapter? = null
    private var mData: Status? = null

    companion object {
        fun newInstance(): StatusFragment = StatusFragment()
        private val TAG = StatusFragment::class.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = AppInjector.obtainViewModel(this)
        mViewModel.getStatusError.observe(this, Observer { onError(it) })
        mViewModel.getStatusSuccess.observe(this, Observer { onSuccess(it) })
        mViewModel.getStatusProgress.observe(this, Observer { onProgress(it) })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_status, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        renderView()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onClick(v: View?) {
        when (v?.id) {

        }
    }

    override fun onSuccess(it: Any?) {
        mData = it as Status?
        renderData()
    }

    override fun onError(t: Throwable?) {

    }

    override fun onProgress(b: Boolean?) {

    }

    private fun renderView() {
        recyclerView_status.layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
        mStatusAdapter = context?.let { StatusRecyclerViewAdapter(it) }
        recyclerView_status.adapter = mStatusAdapter

        recyclerView_facilities.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mFacilitiesAdapter = FacilitiesRecyclerViewAdapter()
        recyclerView_facilities.adapter = mFacilitiesAdapter
    }

    private fun renderData() {
        if (mData?.facilities != null)
            mFacilitiesAdapter?.setData(mData?.facilities!!)
    }
}