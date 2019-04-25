package com.neo.goservice.animations

import android.graphics.Matrix
import android.util.Log
import android.view.animation.Animation
import android.view.animation.Transformation

class WheelTranslationAnimation : Animation() {

    private var hight: Int = 0;
    private var weight: Int = 0;
    override fun initialize(width: Int, height: Int, parentWidth: Int, parentHeight: Int) {
        Log.d("neo", "width = $width")
        Log.d("neo", "height = $height")
        Log.d("neo", "parentWidth = $parentWidth")
        Log.d("neo", "parentHeight = $parentHeight")
        hight = height
        weight = width
        super.initialize(width, height, parentWidth, parentHeight)
    }

    override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
        val matrix: Matrix? = t?.matrix
        matrix?.postTranslate((weight/2).toFloat(), (weight/2).toFloat())
    }
}