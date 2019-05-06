package com.neo.goservice.pages.status

import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.neo.goservice.R
import com.neo.goservice.repository.data.Facilities
import com.neo.goservice.repository.data.Machines
import kotlinx.android.synthetic.main.item_facilities.view.*
import kotlinx.android.synthetic.main.item_status.view.textView_title

class FacilitiesRecyclerViewAdapter() : RecyclerView.Adapter<FacilitiesRecyclerViewAdapter.ViewHolder>() {
    private var mData: ArrayList<Facilities> = ArrayList()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view: View = LayoutInflater.from(p0.context).inflate(R.layout.item_facilities, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bind()
        mData[p1].let {
            p0.itemView.textView_title.text = it.title
            it.machines?.let { it1 ->
                p0.setData(it1)
            }
        }

    }

    fun setData(data: ArrayList<Facilities>) {
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var mAdapter: MachineRecyclerViewAdapter? = null

        fun bind() {
            mAdapter = MachineRecyclerViewAdapter(itemView.recyclerView_machine)
            itemView.recyclerView_machine.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.VERTICAL, false)
            itemView.recyclerView_machine.adapter = mAdapter
        }

        fun setData(data: ArrayList<Machines>) {
            mAdapter?.setData(data)
        }
    }
}