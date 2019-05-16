package com.neo.goservice.pages.evaluate

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.neo.goservice.R

class MachineRecyclerViewAdapter : RecyclerView.Adapter<MachineRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view: View = LayoutInflater.from(p0.context).inflate(R.layout.item_price_evaluate_machine, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind() {}
    }
}