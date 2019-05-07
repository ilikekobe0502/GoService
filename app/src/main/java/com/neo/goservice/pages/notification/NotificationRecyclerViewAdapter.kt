package com.neo.goservice.pages.notification

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.neo.goservice.R
import com.neo.goservice.repository.data.Content
import kotlinx.android.synthetic.main.item_notification.view.*
import kotlinx.android.synthetic.main.item_notification.view.textView_title

class NotificationRecyclerViewAdapter : RecyclerView.Adapter<NotificationRecyclerViewAdapter.ViewHolder>() {
    private var mData: ArrayList<Content> = ArrayList()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view: View = LayoutInflater.from(p0.context).inflate(R.layout.item_notification, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bind()
        val item = mData[p1]
        if (item != null) {
            p0.itemView.textView_title.text = item.title
            p0.itemView.textView_date.text = item.time
            p0.itemView.textView_content_date.text = item.time
            p0.itemView.textView_message.text = item.message
            if (item.read == 0) {
                p0.itemView.textView_title.setTextColor(ContextCompat.getColor(p0.itemView.context, R.color.withe50))
            } else {
                p0.itemView.textView_title.setTextColor(ContextCompat.getColor(p0.itemView.context, android.R.color.white))
            }

            p0.showMessage(item.expend == 1)
            p0.itemView.setOnClickListener {
                if (item.expend == 1) {
                    item.expend = 0
                } else {
                    item.expend = 1
                }
                notifyItemChanged(p1)
            }
        } else {
            return
        }
    }

    fun setData(data: ArrayList<Content>?) {
        if (data == null)
            return
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    open class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind() {}

        fun showMessage(show: Boolean) {
            if (show) {
                itemView.layout_message.visibility = View.VISIBLE
                itemView.imageView_arrow.setBackgroundResource(R.drawable.ic_arrow_down_extend)
            } else {
                itemView.layout_message.visibility = View.GONE
                itemView.imageView_arrow.setBackgroundResource(R.drawable.ic_arrow_up_collapse)
            }
        }
    }
}