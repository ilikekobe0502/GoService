package com.neo.goservice.pages.facilities_info

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import com.neo.goservice.R
import com.neo.goservice.pages.base.InteractionView
import com.neo.goservice.pages.base.OnPageInteractionListener
import com.neo.goservice.repository.data.AirCompressor
import kotlinx.android.synthetic.main.fragment_facilities_info_content.*

class ContentFragment : InteractionView<OnPageInteractionListener.Primary>(), View.OnFocusChangeListener,
        View.OnClickListener {

    private var mData: ArrayList<AirCompressor> = ArrayList()
    private val mAdapter: ContentRecyclerViewAdapter = ContentRecyclerViewAdapter()

    companion object {
        fun newInstance(): ContentFragment = ContentFragment()
        private val TAG = ContentFragment::class.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments?.get(TAG_ARG) is ArrayList<*>) {
            mData.addAll(arguments?.get(TAG_ARG) as Collection<AirCompressor>)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_facilities_info_content, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        renderView()
    }

    override fun onStart() {
        super.onStart()
        renderData()
    }

    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        when (v?.id) {

        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {

        }
    }

    private fun renderView() {
        recyclerView_content.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView_content.adapter = mAdapter
    }

    private fun renderData() {
        mAdapter.setData(mData)
    }
}