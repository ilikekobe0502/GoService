package com.neo.goservice.pages.status

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.neo.goservice.R
import com.neo.goservice.repository.data.Machines
import kotlinx.android.synthetic.main.item_machine.view.*

class MachineRecyclerViewAdapter(private val mRecyclerView: RecyclerView?) : RecyclerView.Adapter<MachineRecyclerViewAdapter.ViewHolder>() {
    private var mData: ArrayList<Machines> = ArrayList()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view: View = LayoutInflater.from(p0.context).inflate(R.layout.item_machine, p0, false)
        return ViewHolder(view, mRecyclerView)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bind()
        if (p1 == mData.size - 1) {
            p0.itemView.imageView_line.visibility = View.INVISIBLE
        } else {
            p0.itemView.imageView_line.visibility = View.VISIBLE
        }
        mData[p1].let {
            p0.itemView.textView_name.text = it.name
        }
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