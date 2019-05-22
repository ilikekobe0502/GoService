package com.neo.goservice.pages.evaluate

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.neo.goservice.R
import com.neo.goservice.repository.data.Machine
import kotlinx.android.synthetic.main.item_price_evaluate_machine.view.*

class MachineRecyclerViewAdapter : RecyclerView.Adapter<MachineRecyclerViewAdapter.ViewHolder>() {
    private var mData: ArrayList<Machine> = ArrayList()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view: View = LayoutInflater.from(p0.context).inflate(R.layout.item_price_evaluate_machine, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bind()
        val item = mData[p1]
        p0.itemView.textView_name.text = item.name
        p0.itemView.textView_full_time.text = item.car_full
        p0.itemView.textView_empty_time.text = item.car_empty
        p0.itemView.progress_empty.progress = item.car_empty!!.toInt()
        p0.itemView.progress_full.progress = item.car_full!!.toInt()
    }

    fun setData(data: ArrayList<Machine>?) {
        if (data == null)
            return
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind() {}
    }
}