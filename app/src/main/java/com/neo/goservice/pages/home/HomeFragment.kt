package com.neo.goservice.pages.home

import android.graphics.Point
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import com.neo.goservice.R
import com.neo.goservice.constants.Page
import com.neo.goservice.pages.base.InteractionView
import com.neo.goservice.pages.base.OnPageInteractionListener
import kotlinx.android.synthetic.main.fragment_home.*
import kotlin.math.log


class HomeFragment : InteractionView<OnPageInteractionListener.Primary>(), View.OnClickListener {
    private lateinit var mViewModel: HomeViewModel
    private val MOVE_ANGLE: Int = 5 //移動的角度
    var mCenterX = 0
    var mCenterY = 0
    var downX: Float = 0f
    var downY: Float = 0f
    var upX: Float = 0f
    var upY: Float = 0f


    var mLastY: Float = 0f//最後移動的Y點

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
        private val TAG = HomeFragment::class.simpleName
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onStart() {
        super.onStart()
        initMainMenu()
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                downX = event.x
                downY = event.y
            }
            MotionEvent.ACTION_UP -> {
                upX = event.getX();
                upY = event.getY();
                var x: Float = Math.abs(upX - downX)
                var y: Float = Math.abs(upY - downY)
                var z: Double = Math.sqrt((x * x + y * y).toDouble())
                var jiaodu: Long = Math.round((Math.asin(y / z) / Math.PI * 180));//角度

                if (upY < downY && jiaodu > 45) {//上
                    Log.d("onTouchEvent-ACTION_UP", "角度:" + jiaodu + ", 動作:上");
                } else if (upY > downY && jiaodu > 45) {//下
                    Log.d("onTouchEvent-ACTION_UP", "角度:" + jiaodu + ", 動作:下");
                } else if (upX < downX && jiaodu <= 45) {
                    //左
                    Log.d("onTouchEvent-ACTION_UP", "角度:" + jiaodu + ", 動作:左");
                    // 原方向不是向右時，方向轉右
//                    if (mDirection != EAST) {
//                        mNextDirection = WEST;
//                    }
                } else if (upX > downX && jiaodu <= 45) {
                    //右
                    Log.d("onTouchEvent-ACTION_UP", "角度:" + jiaodu + ", 動作:右");
                    // 原方向不是向左時，方向轉右
//                    if (mDirection != WEST) {
//                        mNextDirection = EAST;
//                    }
                }
            }
            MotionEvent.ACTION_MOVE -> {
                val paramsStatus = imageButton_status.layoutParams as ConstraintLayout.LayoutParams
                paramsStatus.circleConstraint = R.id.imageView_center
                paramsStatus.circleRadius = resources.getDimension(R.dimen._143sdp).toInt()
                setViewDisplay(imageButton_status, textView_status, imageButton_status.left / 2 > 0)

                val paramsFromQuery = imageButton_form_query.layoutParams as ConstraintLayout.LayoutParams
                paramsFromQuery.circleConstraint = R.id.imageView_center
                paramsFromQuery.circleRadius = resources.getDimension(R.dimen._143sdp).toInt()
                setViewDisplay(imageButton_form_query, textView_form_query, imageButton_form_query.left / 2 > 0)

                val paramsFacilityInfo = imageButton_facility_info.layoutParams as ConstraintLayout.LayoutParams
                paramsFacilityInfo.circleConstraint = R.id.imageView_center
                paramsFacilityInfo.circleRadius = resources.getDimension(R.dimen._143sdp).toInt()
                setViewDisplay(imageButton_facility_info, textView_facility_info, imageButton_facility_info.left / 2 > 0)

                val paramsHistoricalAlert = imageButton_historical_alert.layoutParams as ConstraintLayout.LayoutParams
                paramsHistoricalAlert.circleConstraint = R.id.imageView_center
                paramsHistoricalAlert.circleRadius = resources.getDimension(R.dimen._143sdp).toInt()
                setViewDisplay(imageButton_historical_alert, textView_historical_alert, imageButton_historical_alert.left / 2 > 0)

                val paramsRealtimeInfo = imageButton_realtime_info.layoutParams as ConstraintLayout.LayoutParams
                paramsRealtimeInfo.circleConstraint = R.id.imageView_center
                paramsRealtimeInfo.circleRadius = resources.getDimension(R.dimen._143sdp).toInt()
                setViewDisplay(imageButton_realtime_info, textView_realtime_info, imageButton_realtime_info.left / 2 > 0)

                val paramsPriceEvaluate = imageButton_price_evaluate.layoutParams as ConstraintLayout.LayoutParams
                paramsPriceEvaluate.circleConstraint = R.id.imageView_center
                paramsPriceEvaluate.circleRadius = resources.getDimension(R.dimen._143sdp).toInt()
                setViewDisplay(imageButton_price_evaluate, textView_price_evaluate, imageButton_price_evaluate.left / 2 > 0)

                val paramsNotification = imageButton_notification.layoutParams as ConstraintLayout.LayoutParams
                paramsNotification.circleConstraint = R.id.imageView_center
                paramsNotification.circleRadius = resources.getDimension(R.dimen._143sdp).toInt()
                setViewDisplay(imageButton_notification, textView_notification, imageButton_notification.left / 2 > 0)

                val paramsHistoricalData = imageButton_historical_data.layoutParams as ConstraintLayout.LayoutParams
                paramsHistoricalData.circleConstraint = R.id.imageView_center
                paramsHistoricalData.circleRadius = resources.getDimension(R.dimen._143sdp).toInt()
                setViewDisplay(imageButton_historical_data, textView_historical_data, imageButton_historical_data.left / 2 > 0)

                val paramsWeb = imageButton_Web.layoutParams as ConstraintLayout.LayoutParams
                paramsWeb.circleConstraint = R.id.imageView_center
                paramsWeb.circleRadius = resources.getDimension(R.dimen._143sdp).toInt()
                setViewDisplay(imageButton_Web, textView_Web, imageButton_Web.left > 0)

                when {
                    event.y < mLastY -> {
                        paramsStatus.circleAngle = (paramsStatus.circleAngle - MOVE_ANGLE)
                        paramsFromQuery.circleAngle = (paramsFromQuery.circleAngle - MOVE_ANGLE)
                        paramsFacilityInfo.circleAngle = (paramsFacilityInfo.circleAngle - MOVE_ANGLE)
                        paramsHistoricalAlert.circleAngle = (paramsHistoricalAlert.circleAngle - MOVE_ANGLE)
                        paramsRealtimeInfo.circleAngle = (paramsRealtimeInfo.circleAngle - MOVE_ANGLE)
                        paramsPriceEvaluate.circleAngle = (paramsPriceEvaluate.circleAngle - MOVE_ANGLE)
                        paramsNotification.circleAngle = (paramsNotification.circleAngle - MOVE_ANGLE)
                        paramsHistoricalData.circleAngle = (paramsHistoricalData.circleAngle - MOVE_ANGLE)
                        paramsWeb.circleAngle = (paramsWeb.circleAngle - MOVE_ANGLE)
                    }
                    event.y > mLastY -> {
                        paramsStatus.circleAngle = (paramsStatus.circleAngle + MOVE_ANGLE)
                        paramsFromQuery.circleAngle = (paramsFromQuery.circleAngle + MOVE_ANGLE)
                        paramsFacilityInfo.circleAngle = (paramsFacilityInfo.circleAngle + MOVE_ANGLE)
                        paramsHistoricalAlert.circleAngle = (paramsHistoricalAlert.circleAngle + MOVE_ANGLE)
                        paramsRealtimeInfo.circleAngle = (paramsRealtimeInfo.circleAngle + MOVE_ANGLE)
                        paramsPriceEvaluate.circleAngle = (paramsPriceEvaluate.circleAngle + MOVE_ANGLE)
                        paramsNotification.circleAngle = (paramsNotification.circleAngle + MOVE_ANGLE)
                        paramsHistoricalData.circleAngle = (paramsHistoricalData.circleAngle + MOVE_ANGLE)
                        paramsWeb.circleAngle = (paramsWeb.circleAngle + MOVE_ANGLE)
                    }
                    else -> return false
                }

                imageButton_status.layoutParams = paramsStatus
                imageButton_form_query.layoutParams = paramsFromQuery
                imageButton_facility_info.layoutParams = paramsFacilityInfo
                imageButton_historical_alert.layoutParams = paramsHistoricalAlert
                imageButton_realtime_info.layoutParams = paramsRealtimeInfo
                imageButton_price_evaluate.layoutParams = paramsPriceEvaluate
                imageButton_notification.layoutParams = paramsNotification
                imageButton_historical_data.layoutParams = paramsHistoricalData
                imageButton_Web.layoutParams = paramsWeb

                mLastY = event.y
            }
        }
        return super.onTouch(v, event)
    }

    override fun onClick(v: View?) {
        var page: Int = Page.LOGIN
        when (v?.id) {
            R.id.imageButton_status -> {
                page = Page.STATUS
            }
            R.id.imageButton_form_query -> {
            }
            R.id.imageButton_facility_info -> {
                page = Page.FACILITIES_INFO
            }
            R.id.imageButton_historical_alert -> {
            }
            R.id.imageButton_realtime_info -> {
            }
            R.id.imageButton_price_evaluate -> {
            }
            R.id.imageButton_notification -> {
                page = Page.NOTIFICATION
            }
            R.id.imageButton_historical_data -> {
            }
            R.id.imageButton_Web -> {
            }
        }
        getInteractionListener().switchPage(R.id.fragment_container, page, Bundle(), true, true)
    }


    private fun initView() {
        val paramsCenter = imageView_center.layoutParams as ConstraintLayout.LayoutParams

        //設置大圈圈，因為無法用寫死的方式將元件設在screen外，所以使用相對距離
        val p: Point = Point()
        activity?.windowManager?.defaultDisplay?.getSize(p)
        paramsCenter.marginEnd = (p.x - resources.getDimension(R.dimen._154sdp)).toInt()
        imageView_center.layoutParams = paramsCenter
    }

    private fun initMainMenu() {

        //init out of round icon
        imageButton_status.post { setViewDisplay(imageButton_status, textView_status, imageButton_status.left / 2 > 0) }
        imageButton_form_query.post { setViewDisplay(imageButton_form_query, textView_form_query, imageButton_form_query.left / 2 > 0) }
        imageButton_facility_info.post { setViewDisplay(imageButton_facility_info, textView_facility_info, imageButton_facility_info.left / 2 > 0) }
        imageButton_historical_alert.post { setViewDisplay(imageButton_historical_alert, textView_historical_alert, imageButton_historical_alert.left / 2 > 0) }
        imageButton_realtime_info.post { setViewDisplay(imageButton_realtime_info, textView_realtime_info, imageButton_realtime_info.left / 2 > 0) }
        imageButton_price_evaluate.post { setViewDisplay(imageButton_price_evaluate, textView_price_evaluate, imageButton_price_evaluate.left / 2 > 0) }
        imageButton_notification.post { setViewDisplay(imageButton_notification, textView_notification, imageButton_notification.left / 2 > 0) }
        imageButton_historical_data.post { setViewDisplay(imageButton_historical_data, textView_historical_data, imageButton_historical_data.left / 2 > 0) }
        imageButton_Web.post { setViewDisplay(imageButton_Web, textView_Web, imageButton_Web.left / 2 > 0) }

        imageButton_status.setOnClickListener(this)
        imageButton_form_query.setOnClickListener(this)
        imageButton_facility_info.setOnClickListener(this)
        imageButton_historical_alert.setOnClickListener(this)
        imageButton_realtime_info.setOnClickListener(this)
        imageButton_price_evaluate.setOnClickListener(this)
        imageButton_notification.setOnClickListener(this)
        imageButton_historical_data.setOnClickListener(this)
        imageButton_Web.setOnClickListener(this)
    }

    private fun setViewDisplay(view1: ImageView, view2: TextView, show: Boolean = true) {
        Log.d("neo", "show $show")
        if (show) {
            view1.visibility = View.VISIBLE
            view2.visibility = View.VISIBLE
        } else {
            view1.visibility = View.INVISIBLE
            view2.visibility = View.INVISIBLE
        }
    }
}