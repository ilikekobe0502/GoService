package com.neo.goservice.pages.facilities_info

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.neo.goservice.R
import com.neo.goservice.repository.data.Machines
import kotlinx.android.synthetic.main.item_machine.view.*

class ContentRecyclerViewAdapter() : RecyclerView.Adapter<ContentRecyclerViewAdapter.ViewHolder>() {
    private var mData: ArrayList<Machines> = ArrayList()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view: View = LayoutInflater.from(p0.context).inflate(R.layout.item_facilities_info_content, p0, false)
        return ViewHolder(view, null)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bind()

    }

    fun setData(data: ArrayList<Machines>) {
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View, private val mRecyclerView: RecyclerView?) : RecyclerView.ViewHolder(view) {
        fun bind() {}
    }
}