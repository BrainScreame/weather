package com.osenov.weather.ui.main

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import androidx.core.graphics.drawable.toBitmap
import android.graphics.Bitmap.createBitmap
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import com.osenov.weather.R
import java.lang.Math.*


class CircleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var percent: Int = 78
        set(value) {
            if (value in 0..100) {
                field = value
                textPercent = "$value%"
                invalidate()
            }
        }

    private var textPercent = "$percent%"

    private var lineAlpha = 0

    private var matrixRotate = Matrix()

    private var textInfo = "Humidity"

    private val radius: Float by lazy {
        height / 2f - (height / 10f)
    }

    private val shaderCircle: SweepGradient by lazy {
        SweepGradient(
            width / 2f,
            height / 2f,
            intArrayOf(
                Color.rgb(200, 191, 235),
                Color.rgb(255, 247, 180),
                Color.rgb(206, 135, 206),
                Color.rgb(206, 249, 231),
                Color.rgb(246, 218, 188),
                Color.rgb(200, 191, 235)
            ),
            floatArrayOf(0.22f, 0.44f, 0.66f, 0.78f, 0.88f, 1.0f)
        )
    }

    private val circlePaint: Paint by lazy {
        Paint().apply {
            isAntiAlias = true
            shader = shaderCircle
        }
    }

    private val whiteCirclePaint = Paint().apply {
        isAntiAlias = true
        color = Color.WHITE
    }


    private val textPaint by lazy {
        Paint().apply {
            typeface = Typeface.DEFAULT_BOLD
            isAntiAlias = true
            color = Color.rgb(13, 18, 63)
            textSize = width / 4.5f
        }
    }

    private val textInfoPaint by lazy {
        Paint().apply {
            isAntiAlias = true
            color = Color.rgb(66, 70, 105)
            textSize = width / 14.5f
        }
    }

    private val paintLine by lazy {
        Paint().apply {
            isAntiAlias = true
            color = Color.rgb(212, 232, 255)
            strokeWidth = width / 108f
            strokeCap = Paint.Cap.ROUND
        }
    }


    private val bitmap by lazy(LazyThreadSafetyMode.NONE) {
        AppCompatResources.getDrawable(context, R.drawable.ic_blood_drop_plain)!!
            .toBitmap((width / 12.27f).toInt(), (height / 10.91f).toInt())
    }

    private val rectTextSizeMeasure = Rect()

    private var rectBitmapSize = Rect()


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = measureSize(widthMeasureSpec) - (paddingLeft + paddingRight)
        val height = measureSize(heightMeasureSpec) - (paddingTop + paddingBottom)
        val circleSize = kotlin.math.min(width, height)
        setMeasuredDimension(circleSize, circleSize)
    }

    private fun measureSize(measureSpec: Int): Int {
        val specSize = MeasureSpec.getSize(measureSpec)
        return when (MeasureSpec.getMode(measureSpec)) {
            MeasureSpec.EXACTLY -> specSize
            MeasureSpec.AT_MOST -> specSize
            else -> 700
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        drawCircles(canvas)
        drawLines(canvas)
        drawTextInfo(canvas)
        drawTextPercent(canvas)
        drawBitmapBlob(percent * 3.6f, canvas)
    }

    private fun drawCircles(canvas: Canvas?) {
        canvas?.drawCircle(
            width.toFloat() / 2,
            height.toFloat() / 2,
            height.toFloat() / 2,
            circlePaint
        )

        canvas?.drawCircle(
            width.toFloat() / 2,
            height.toFloat() / 2,
            height.toFloat() / 2 - (height.toFloat() / 10),
            whiteCirclePaint
        )
    }

    private fun drawLines(canvas: Canvas?) {
        lineAlpha = 0
        while (lineAlpha < 360) {
            lineAlpha += 30
            canvas?.drawLine(
                width / 2f + (radius * 0.8 * kotlin.math.cos((lineAlpha - 90) * PI / 180)).toFloat(),
                height / 2f + (radius * 0.8 * kotlin.math.sin((lineAlpha - 90) * PI / 180)).toFloat(),
                width / 2f + (radius * 0.9 * kotlin.math.cos((lineAlpha - 90) * PI / 180)).toFloat(),
                height / 2f + (radius * 0.9 * kotlin.math.sin((lineAlpha - 90) * PI / 180)).toFloat(),
                paintLine
            )
        }
    }

    private fun drawTextPercent(canvas: Canvas?) {
        canvas?.drawText(
            textPercent,
            width / 2f - (textPaint.measureText(textPercent) / 2),
            height / 2f,
            textPaint
        )
    }

    private fun drawTextInfo(canvas: Canvas?) {
        textInfoPaint.getTextBounds(textInfo, 0, textInfo.length, rectTextSizeMeasure)

        canvas?.drawText(
            textInfo,
            width / 2f - (textInfoPaint.measureText(textInfo) / 2f),
            height / 2f + (height / 2f - (height / 10f)) / 2f - rectTextSizeMeasure.height(),
            textInfoPaint
        )
    }

    private fun drawBitmapBlob(alpha: Float, canvas: Canvas?) {
        matrixRotate.setRotate(alpha)
        val bitmapRotate =
            createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrixRotate, true)
        rectBitmapSize.set(
            width / 2 + (radius * 0.8 * kotlin.math.cos((alpha - 90) * PI / 180)).toInt() - bitmapRotate.width / 2,
            height / 2 + (radius * 0.8 * kotlin.math.sin((alpha - 90) * PI / 180)).toInt() - bitmapRotate.height / 2,
            width / 2 + (radius * 0.8 * kotlin.math.cos((alpha - 90) * PI / 180)).toInt() + bitmapRotate.width / 2,
            height / 2 + (radius * 0.8 * kotlin.math.sin((alpha - 90) * PI / 180)).toInt() + bitmapRotate.height / 2
        )
        canvas?.drawBitmap(bitmapRotate, null, rectBitmapSize, paintLine)
    }


    private val gestureDetector =
        GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
            override fun onLongPress(e: MotionEvent?) {
                Toast.makeText(context, "click!", Toast.LENGTH_LONG).show()
                percentAnimator()
            }
        })

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return gestureDetector.onTouchEvent(event)
    }

    fun percentAnimator() {
        val percentValue = percent
        ValueAnimator.ofInt(0, percentValue).apply {
            duration = 2000
            interpolator = AccelerateDecelerateInterpolator()
            addUpdateListener {
                percent = it.animatedValue as Int
            }
            start()
        }
    }

    fun setPercentValue(value: Int) {
        percent = value
    }
}

