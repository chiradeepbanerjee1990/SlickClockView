package com.chiradeep.clock.clockview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.chiradeep.clock.R


class SlickClockView  :
    View{

    constructor(context: Context?) : super(context,null) {
        init(null)
    }

    constructor(context: Context?, attr: AttributeSet) : super(context,attr) {
        init(attr)
    }

    constructor(context: Context?, attr: AttributeSet, defStyle: Int) : super(context,attr, defStyle) {
        init(attr)
    }

    private var outerCircleRadius : Float = 200f
    private var innerCircleRadius : Float = 198.5f

    private var outerCirclePaint : Paint = Paint()
    private var innerCirclePaint : Paint = Paint()

    fun init(attr: AttributeSet?=null){

        outerCirclePaint.isAntiAlias = true
        outerCirclePaint.strokeWidth = 3f
        outerCirclePaint.color = context.resources.getColor(R.color.clockcolor)
        outerCirclePaint.style = Paint.Style.FILL
        outerCirclePaint.setShadowLayer(10f,0f,0f,context.resources.getColor(R.color.clockcolor))
        setLayerType(LAYER_TYPE_SOFTWARE,outerCirclePaint)

        innerCirclePaint.isAntiAlias = true
        innerCirclePaint.strokeWidth = 3f
        innerCirclePaint.color = context.resources.getColor(R.color.innerclockcolor)
        innerCirclePaint.style = Paint.Style.FILL



    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        var viewHeight = height/2
        var viewWidth = width/2

        canvas?.drawCircle(viewWidth.toFloat(),
            viewHeight.toFloat(),
            outerCircleRadius*2,
            outerCirclePaint)

        canvas?.drawCircle(viewWidth.toFloat(),
            viewHeight.toFloat(),
            innerCircleRadius*2,
            innerCirclePaint)

    }
}