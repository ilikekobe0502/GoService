package com.neo.goservice.pages.home

import android.graphics.Point
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.util.Log
import android.view.*
import com.neo.goservice.R
import com.neo.goservice.pages.base.InteractionView
import com.neo.goservice.pages.base.OnPageInteractionListener
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : InteractionView<OnPageInteractionListener.Primary>() {

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
        val paramsCenter = imageView_center.layoutParams as ConstraintLayout.LayoutParams

        //設置大圈圈，因為無法用寫死的方式將元件設在screen外，所以使用相對距離
        val p: Point = Point()
        activity?.windowManager?.defaultDisplay?.getSize(p)
        paramsCenter.marginEnd = (p.x - resources.getDimension(R.dimen._170sdp)).toInt()
        imageView_center.layoutParams = paramsCenter

    }

    override fun onStart() {
        super.onStart()
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        val X = event?.x // 觸控的 X 軸位置
        val Y = event?.y // 觸控的 Y 軸位置

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

                val paramsFromQuery = imageButton_form_query.layoutParams as ConstraintLayout.LayoutParams
                paramsFromQuery.circleConstraint = R.id.imageView_center
                paramsFromQuery.circleRadius = resources.getDimension(R.dimen._143sdp).toInt()

                val paramsFacilityInfo = imageButton_facility_info.layoutParams as ConstraintLayout.LayoutParams
                paramsFacilityInfo.circleConstraint = R.id.imageView_center
                paramsFacilityInfo.circleRadius = resources.getDimension(R.dimen._143sdp).toInt()

                val paramsHistoricalAlert = imageButton_historical_alert.layoutParams as ConstraintLayout.LayoutParams
                paramsHistoricalAlert.circleConstraint = R.id.imageView_center
                paramsHistoricalAlert.circleRadius = resources.getDimension(R.dimen._143sdp).toInt()

                val paramsRealtimeInfo = imageButton_realtime_info.layoutParams as ConstraintLayout.LayoutParams
                paramsRealtimeInfo.circleConstraint = R.id.imageView_center
                paramsRealtimeInfo.circleRadius = resources.getDimension(R.dimen._143sdp).toInt()

                val paramsPriceEvaluate = imageButton_price_evaluate.layoutParams as ConstraintLayout.LayoutParams
                paramsPriceEvaluate.circleConstraint = R.id.imageView_center
                paramsPriceEvaluate.circleRadius = resources.getDimension(R.dimen._143sdp).toInt()

                val paramsNotification = imageButton_notification.layoutParams as ConstraintLayout.LayoutParams
                paramsNotification.circleConstraint = R.id.imageView_center
                paramsNotification.circleRadius = resources.getDimension(R.dimen._143sdp).toInt()

                val paramsHistoricalData = imageButton_historical_data.layoutParams as ConstraintLayout.LayoutParams
                paramsHistoricalData.circleConstraint = R.id.imageView_center
                paramsHistoricalData.circleRadius = resources.getDimension(R.dimen._143sdp).toInt()

                val paramsWeb = imageButton_Web.layoutParams as ConstraintLayout.LayoutParams
                paramsWeb.circleConstraint = R.id.imageView_center
                paramsWeb.circleRadius = resources.getDimension(R.dimen._143sdp).toInt()
                if (event.y < mLastY) {
                    paramsStatus.circleAngle = (paramsStatus.circleAngle + MOVE_ANGLE)
                    paramsFromQuery.circleAngle = (paramsFromQuery.circleAngle + MOVE_ANGLE)
                    paramsFacilityInfo.circleAngle = (paramsFacilityInfo.circleAngle + MOVE_ANGLE)
                    paramsHistoricalAlert.circleAngle = (paramsHistoricalAlert.circleAngle + MOVE_ANGLE)
                    paramsRealtimeInfo.circleAngle = (paramsRealtimeInfo.circleAngle + MOVE_ANGLE)
                    paramsPriceEvaluate.circleAngle = (paramsPriceEvaluate.circleAngle + MOVE_ANGLE)
                    paramsNotification.circleAngle = (paramsNotification.circleAngle + MOVE_ANGLE)
                    paramsHistoricalData.circleAngle = (paramsHistoricalData.circleAngle + MOVE_ANGLE)
                    paramsWeb.circleAngle = (paramsWeb.circleAngle + MOVE_ANGLE)
                } else {
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
}