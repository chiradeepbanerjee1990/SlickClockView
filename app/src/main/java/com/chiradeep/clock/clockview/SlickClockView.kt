package com.chiradeep.clock.clockview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import com.chiradeep.clock.R
import kotlin.math.cos
import kotlin.math.sin


class SlickClockView  :
    View{

    private val numbers = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
    private val clockText = "|"
    private var rect = Rect()
    private var padding = 45

    constructor(context: Context?) : super(context,null) {
        init(null)
    }

    constructor(context: Context?, attr: AttributeSet) : super(context,attr) {
        init(attr)
    }

    constructor(context: Context?, attr: AttributeSet, defStyle: Int) : super(context,attr, defStyle) {
        init(attr)
    }

    private var outerCircleRadius : Float = 0f
    private var innerCircleRadius : Float = outerCircleRadius

    private var elevationRadius : Float = 0f

    private var outerCirclePaint : Paint = Paint()
    private var innerCirclePaint : Paint = Paint()

    var paint = Paint()




    fun init(attr: AttributeSet?=null){

        var tf = context.obtainStyledAttributes(attr,R.styleable.SlickClockView)

        outerCircleRadius = tf.getFloat(R.styleable.SlickClockView_outerradius,0f)
        elevationRadius = tf.getFloat(R.styleable.SlickClockView_shadowradius,0f)
        innerCircleRadius = outerCircleRadius - 1.5f



        outerCirclePaint.isAntiAlias = true
        outerCirclePaint.strokeWidth = 3f
        outerCirclePaint.color = context.resources.getColor(R.color.clockcolor)
        outerCirclePaint.style = Paint.Style.FILL
        outerCirclePaint.setShadowLayer(elevationRadius,0f,0f,context.resources.getColor(R.color.clockcolor))
        setLayerType(LAYER_TYPE_SOFTWARE,outerCirclePaint)



        innerCirclePaint.isAntiAlias = true
        innerCirclePaint.strokeWidth = 3f
        innerCirclePaint.color = context.resources.getColor(R.color.innerclockcolor)
        innerCirclePaint.style = Paint.Style.FILL



        paint.color = Color.BLACK
        paint.strokeWidth = 8f
        paint.style = Paint.Style.FILL
        paint.isAntiAlias = true





        tf.recycle()

    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        var viewHeight = height/2
        var viewWidth = width/2

        canvas?.save()

        canvas?.drawCircle(viewWidth.toFloat(),
            viewHeight.toFloat(),
            outerCircleRadius*2,
            outerCirclePaint)

        canvas?.drawCircle(viewWidth.toFloat(),
            viewHeight.toFloat(),
            innerCircleRadius*2,
            innerCirclePaint)

        canvas?.drawCircle(viewWidth.toFloat(),
            viewHeight.toFloat(),
            8f,
            paint)


        paint.textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,15f,context.resources.displayMetrics)

        for (number in numbers) {
            paint.getTextBounds(clockText, 0, clockText.length, rect)
            val angle = Math.PI / 6 * (number - 3)
            val x = (width/2  + cos(angle) * (innerCircleRadius*2 - padding)- rect.width() / 2).toFloat()
            val y = (height/2  + sin(angle) * (innerCircleRadius*2 - padding) + rect.height() / 2).toFloat()
            canvas?.drawText(clockText, x, y, paint)

        }


    }




}