package com.neo.goservice.pages.evaluate

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import android.widget.ArrayAdapter
import com.neo.goservice.AppInjector
import com.neo.goservice.R
import com.neo.goservice.interfaces.ViewModelCallbackListener
import com.neo.goservice.pages.base.InteractionView
import com.neo.goservice.pages.base.OnPageInteractionListener
import com.neo.goservice.repository.data.PriceEvaluate
import com.neo.goservice.utils.MiscUtils
import kotlinx.android.synthetic.main.fragment_price_evaluate.*
import kotlinx.android.synthetic.main.view_tool_bar_title.*


class PriceEvaluateFragment : InteractionView<OnPageInteractionListener.Primary>(), View.OnFocusChangeListener,
        View.OnClickListener, ViewModelCallbackListener {
    private lateinit var mViewModel: PriceEvaluateViewModel

    private var mAdapter: MachineRecyclerViewAdapter = MachineRecyclerViewAdapter()
    private var mTimeAdapter: ArrayAdapter<String>? = null
    private var mCoinAdapter: ArrayAdapter<String>? = null

    private var mData: PriceEvaluate? = null

    companion object {
        fun newInstance(): PriceEvaluateFragment = PriceEvaluateFragment()
        private val TAG = PriceEvaluateFragment::class.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = AppInjector.obtainViewModel(this)
        mViewModel.getEvaluateError.observe(this, Observer { onError(it) })
        mViewModel.getEvaluateSuccess.observe(this, Observer { onSuccess(it) })
        mViewModel.getEvaluateProgress.observe(this, Observer { onProgress(it) })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_price_evaluate, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()

        renderView()
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
        mData = it as PriceEvaluate?
        renderData()
    }

    override fun onError(t: Throwable?) {

    }

    override fun onProgress(b: Boolean?) {
    }

    private fun renderView() {
        MiscUtils.showSoftInput(context, editText2)
        textView_title.setText(R.string.home_price_evaluate)
        recyclerView_machine.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView_machine.adapter = mAdapter



        mTimeAdapter = ArrayAdapter(
                context!!,
                R.layout.view_price_evaluate_spinner,
                R.id.text,
                resources.getStringArray(R.array.price_evaluate_times)
        )
        mTimeAdapter!!.setDropDownViewResource(R.layout.view_lang_dorpdown)
        mCoinAdapter = ArrayAdapter(
                context!!,
                R.layout.view_price_evaluate_spinner,
                R.id.text,
                resources.getStringArray(R.array.price_evaluate_coins)
        )
        mCoinAdapter!!.setDropDownViewResource(R.layout.view_lang_dorpdown)

        spinner_time.adapter = mTimeAdapter
        spinner_currency.adapter = mCoinAdapter
    }

    private fun renderData() {
        mAdapter.setData(mData?.price_evaluate?.machine)
//        viewPager.adapter = InfoViewPagerAdapter()
    }
}