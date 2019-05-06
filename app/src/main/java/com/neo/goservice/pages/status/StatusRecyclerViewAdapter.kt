package com.neo.goservice.pages.status

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.neo.goservice.R
import kotlinx.android.synthetic.main.item_status.view.*

class StatusRecyclerViewAdapter() : RecyclerView.Adapter<StatusRecyclerViewAdapter.ViewHolder>() {
    private var mData: Array<out String>? = null

    constructor(context: Context) : this() {
        mData = context.resources.getStringArray(R.array.status_list)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view: View = LayoutInflater.from(p0.context).inflate(R.layout.item_status, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (mData != null) mData?.size!! else 0
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bind()
        p0.itemView.textView_title.text = mData?.get(p1) ?: ""
        p0.itemView.textView_number.text = "20"
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind() {}
    }
}