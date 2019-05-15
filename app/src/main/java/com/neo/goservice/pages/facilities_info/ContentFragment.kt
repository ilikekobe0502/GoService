package com.neo.goservice.pages.facilities_info

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import com.neo.goservice.R
import com.neo.goservice.interfaces.ViewModelCallbackListener
import com.neo.goservice.pages.base.InteractionView
import com.neo.goservice.pages.base.OnPageInteractionListener
import kotlinx.android.synthetic.main.fragment_device_info_content.*

class ContentFragment : InteractionView<OnPageInteractionListener.Primary>(), View.OnFocusChangeListener,
        View.OnClickListener, ViewModelCallbackListener {

//    private var mData: Notifications? = null

    companion object {
        fun newInstance(): ContentFragment = ContentFragment()
        private val TAG = ContentFragment::class.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_device_info_content, container, false)
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
//        mData = it as Notifications?
        renderData()
    }

    override fun onError(t: Throwable?) {

    }

    override fun onProgress(b: Boolean?) {

    }

    private fun renderView() {
        recyclerView_content.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView_content.adapter = ContentRecyclerViewAdapter()
    }

    private fun renderData() {
    }
}