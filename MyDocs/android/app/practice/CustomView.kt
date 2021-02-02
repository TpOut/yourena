package com.example.kotlinpractice

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.ResourcesCompat

/**
 * Created by TpOut on 2021/2/2.<br>
 * Email address: 416756910@qq.com<br>
 */
class CustomView : View {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private val paint: Paint = Paint().apply {
        color = ResourcesCompat.getColor(resources, R.color.colorAccent, resources.newTheme())
    }

    private val rect = Rect(15, 15, 385, 385)
    private var bitmap: Bitmap? = null
    private var path: Path = Path()

    private fun init() {
        bitmap = createBitmapFromDrawable(resources, R.mipmap.ic_launcher, 100, 100)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        bitmap?.let {
            canvas?.drawBitmap(it, 50f, 50f, paint)
        }
        applyPathPaint()
        applyPath()
        canvas?.drawPath(path, paint)
        applyPaintArc()
        // 3 点钟方向是0 度，逆时针旋转
        canvas?.drawArc(0f, 320f, 400f, 370f, 30f, 60f, true, paint)
        applyPointPaint()
        canvas?.drawPoint(50f, 50f, paint)
        applyStrokePaint()
        canvas?.drawRect(rect, paint)

    }

    private fun applyPath() {
        path.apply {
            moveTo(40f, 300f)
            lineTo(360f, 300f)
            lineTo(200f, 320f)
//            lineTo(40f, 300f)
            close()

            addCircle(60f,280f, 10f, Path.Direction.CW)
            addCircle(75f,280f, 10f, Path.Direction.CW)
            fillType = Path.FillType.EVEN_ODD
        }
    }

    private fun applyPathPaint() {
        paint.apply {
            style = Paint.Style.FILL
        }
    }

    private fun applyPaintArc() {
        paint.apply {
            style = Paint.Style.STROKE
        }
    }

    private fun applyPointPaint() {
        paint.apply {
            strokeCap = Paint.Cap.ROUND
            strokeWidth = 10f
            isAntiAlias = true
        }
    }

    private fun applyStrokePaint() {
        paint.apply {
            style = Paint.Style.STROKE
            strokeWidth = 30f
            strokeMiter = 2f
        }
    }

}