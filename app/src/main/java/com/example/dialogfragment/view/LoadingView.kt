package com.example.dialogfragment.view

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

open class LoadingView@JvmOverloads constructor(context: Context,
                                                      attrs: AttributeSet? = null,
                                                      defStyleAttr: Int = 0) : View(context,attrs,defStyleAttr) {

    private val TAG = "SwitchButton"

    private var mPaint = Paint()
    private var ballColor = 0

    private var centerPointX = 0
    private var centerPointY = 0
    private var ballNumber = 8 //默认小球数量为6

    private var loadRadius = 0
    private var ballRadius = 3F

    private var isShow = false


    //private lateinit var mListener: onSwitchListener

    init {
        //在控件被点击时重新绘制 UI
        setOnClickListener {
            //startAnimate()
        }
    }

    override fun onMeasure(widthMeasureSpec:Int,heightMeasureSpec:Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        mPaint.color = Color.GRAY
        centerPointX = width/2
        centerPointY = height/2
        loadRadius = width/4
        for (i in 1 .. ballNumber) {
            //开始画
            var x =  (centerPointX + loadRadius * sin((i-1)*Math.PI*2/ballNumber).toFloat())
            var y = (centerPointY + loadRadius * cos((i-1)*Math.PI*2/ballNumber).toFloat())
            mPaint.alpha = 255*(ballNumber-i)/ballNumber
            canvas?.drawCircle(x, y, ballRadius, mPaint)
        }
        val animatorRotate: ObjectAnimator = ObjectAnimator.ofFloat(this, "rotation", 0F, 360F)
        animatorRotate.duration = 200
        animatorRotate.repeatCount = ValueAnimator.INFINITE
        //插值器为线性
        animatorRotate.interpolator = LinearInterpolator()
        animatorRotate.start()
    }


}
