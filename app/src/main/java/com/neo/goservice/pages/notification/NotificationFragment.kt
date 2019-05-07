package com.neo.goservice.pages.notification

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SimpleItemAnimator
import android.view.*
import com.neo.goservice.AppInjector
import com.neo.goservice.R
import com.neo.goservice.interfaces.ViewModelCallbackListener
import com.neo.goservice.pages.base.InteractionView
import com.neo.goservice.pages.base.OnPageInteractionListener
import com.neo.goservice.repository.data.Notifications
import kotlinx.android.synthetic.main.fragment_notification.*

class NotificationFragment : InteractionView<OnPageInteractionListener.Primary>(), View.OnFocusChangeListener,
        View.OnClickListener, ViewModelCallbackListener {
    private lateinit var mViewModel: NotificationViewModel
    private var mAdapter: NotificationRecyclerViewAdapter = NotificationRecyclerViewAdapter()

    private var mData: Notifications? = null

    companion object {
        fun newInstance(): NotificationFragment = NotificationFragment()
        private val TAG = NotificationFragment::class.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = AppInjector.obtainViewModel(this)
        mViewModel.getNotificationError.observe(this, Observer { })
        mViewModel.getNotificationSuccess.observe(this, Observer { onSuccess(it) })
        mViewModel.getNotificationProgress.observe(this, Observer { })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_notification, container, false)
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
            R.id.textView_read -> {
                //TODO call 已讀 api
            }
        }
    }

    override fun onSuccess(it: Any?) {
        mData = it as Notifications?
        renderData()
    }

    override fun onError(t: Throwable?) {

    }

    override fun onProgress(b: Boolean?) {

    }

    private fun renderView() {
        textView_read.text = getText(R.string.notification_tag_to_read)
        recyclerView_content.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView_content.adapter = mAdapter
        val line = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        line.setDrawable(ContextCompat.getDrawable(context!!, R.drawable.rectangle_notification_line)!!)
        recyclerView_content.addItemDecoration(line)
        ((recyclerView_content.itemAnimator) as SimpleItemAnimator).supportsChangeAnimations = false
    }

    private fun renderData() {
        mAdapter.setData(mData?.notifications)
    }
}