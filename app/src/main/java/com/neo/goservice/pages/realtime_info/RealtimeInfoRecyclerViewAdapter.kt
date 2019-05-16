package com.neo.goservice.pages.realtime_info

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.neo.goservice.R
import com.neo.goservice.repository.data.AirCompressor
import kotlinx.android.synthetic.main.item_facilities_info_content.view.*

class RealtimeInfoRecyclerViewAdapter() : RecyclerView.Adapter<RealtimeInfoRecyclerViewAdapter.ViewHolder>() {
    private var mData: ArrayList<AirCompressor> = ArrayList()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view: View = LayoutInflater.from(p0.context).inflate(R.layout.item_facilities_info_content, p0, false)
        return ViewHolder(view, null)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bind()
        mData[p1].let {
            p0.itemView.textView_title.text = it.title
            p0.itemView.textView_description.text = it.description
            p0.itemView.textView_effective_time.text = it.effective_time
            p0.itemView.textView_warranty_time_start.text = it.warranty_time_start
            p0.itemView.textView_warranty_time_end.text = it.warranty_time_end
            p0.itemView.textView_name.text = it.name
            p0.itemView.textView_area.text = it.area
            p0.itemView.textView_model.text = it.model
            p0.itemView.textView_engineer.text = it.engineer

            if (it.show) {
                p0.itemView.imageView_extend.setBackgroundResource(R.drawable.ic_arrow_up_collapse)
                p0.itemView.layout_extension.visibility = View.VISIBLE
            } else {
                p0.itemView.imageView_extend.setBackgroundResource(R.drawable.ic_arrow_down_extend)
                p0.itemView.layout_extension.visibility = View.GONE
            }

            p0.itemView.imageView_extend.tag = p1
            p0.itemView.imageView_extend.setOnClickListener { v ->
                /**
                 * Called when a view has been clicked.
                 *
                 * @param v The view that was clicked.
                 */
                it.show = !it.show
                notifyItemChanged(v?.tag as Int)
            }

        }
    }

    fun setData(data: ArrayList<AirCompressor>?) {
        if (data != null) {
            mData.clear()
            mData.addAll(data)
            notifyDataSetChanged()
        }
    }

    class ViewHolder(view: View, private val mRecyclerView: RecyclerView?) : RecyclerView.ViewHolder(view) {
        fun bind() {}
    }
}