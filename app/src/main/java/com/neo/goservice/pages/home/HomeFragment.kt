package com.neo.goservice.pages.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.neo.goservice.R
import com.neo.goservice.pages.TemplateViewModel
import com.neo.goservice.pages.base.InteractionView
import com.neo.goservice.pages.base.OnPageInteractionListener

class HomeFragment : InteractionView<OnPageInteractionListener.Primary>() {

    private lateinit var mViewModel: TemplateViewModel
    private var mAdapter = FunctionsAdapter()

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
        private val TAG = HomeFragment::class.simpleName
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onStart() {
        super.onStart()

    }
}