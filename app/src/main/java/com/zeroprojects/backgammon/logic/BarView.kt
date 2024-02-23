package com.zeroprojects.backgammon.logic

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.graphics.*

class BarView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

    private val rectPaint = Paint().apply {
        color = Color.BLUE
        style = Paint.Style.FILL
    }

    private val trianglePaint = Paint().apply {
        color = Color.RED // Color of the triangle
        style = Paint.Style.FILL
    }

    private val path = Path()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val rectWidth = width.toFloat() * 0.8f // Width of the rectangle (80% of the view width)
        val rectHeight = height.toFloat() * 0.5f // Height of the rectangle (50% of the view height)
        val rectLeft = (width - rectWidth) / 2 // Left position of the rectangle
        val rectTop = (height - rectHeight) / 2 // Top position of the rectangle
        val rectRight = rectLeft + rectWidth // Right position of the rectangle
        val rectBottom = rectTop + rectHeight // Bottom position of the rectangle

        // Update rectPaint color and style if needed
        rectPaint.color = Color.BLACK
        rectPaint.style = Paint.Style.FILL

        // Draw the transparent rectangle
        canvas.drawRect(rectLeft, rectTop, rectRight, rectBottom, rectPaint)

        // Update trianglePaint color and style if needed
        trianglePaint.color = Color.RED // Color of the triangle
        trianglePaint.style = Paint.Style.FILL

        // Update path for the triangle
        path.reset()
        path.moveTo(rectLeft + rectWidth / 2, rectTop) // Bottom center of the rectangle
        path.lineTo(rectLeft, rectBottom) // Top left corner of the rectangle
        path.lineTo(rectRight, rectBottom) // Top right corner of the rectangle
        path.close() // Close the path to form a triangle

        // Draw the triangle
        canvas.drawPath(path, trianglePaint)
    }
}