package com.neo.goservice.pages.evaluate

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import android.widget.ArrayAdapter
import com.neo.goservice.AppInjector
import com.neo.goservice.R
import com.neo.goservice.pages.base.InteractionView
import com.neo.goservice.pages.base.OnPageInteractionListener
import kotlinx.android.synthetic.main.fragment_price_evaluate.*
import kotlinx.android.synthetic.main.view_tool_bar_title.*

class PriceEvaluateFragment : InteractionView<OnPageInteractionListener.Primary>(), View.OnFocusChangeListener, View.OnClickListener {
    private lateinit var mViewModel: PriceEvaluateViewModel

    private var mAdapter: MachineRecyclerViewAdapter = MachineRecyclerViewAdapter()
    private var mTimeAdapter: ArrayAdapter<String>? = null
    private var mCoinAdapter: ArrayAdapter<String>? = null

    companion object {
        fun newInstance(): PriceEvaluateFragment = PriceEvaluateFragment()
        private val TAG = PriceEvaluateFragment::class.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = AppInjector.obtainViewModel(this)
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

    private fun renderView() {
        textView_title.setText(R.string.home_price_evaluate)
        recyclerView_machine.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView_machine.adapter = mAdapter


        mTimeAdapter = ArrayAdapter(context!!, R.layout.view_lang_dorpdown, R.id.text, resources.getStringArray(R.array.price_evaluate_times))
        mCoinAdapter = ArrayAdapter(context!!, R.layout.view_lang_dorpdown, R.id.text, resources.getStringArray(R.array.price_evaluate_coins))

        spinner_time.adapter = mTimeAdapter
        spinner_currency.adapter = mCoinAdapter
    }
}