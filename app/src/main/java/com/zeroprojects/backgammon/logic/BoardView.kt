package com.zeroprojects.backgammon.logic

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.zeroprojects.backgammon.R
import com.zeroprojects.backgammon.base.ApplicationLoader
import com.zeroprojects.backgammon.utils.DimensionUtils
import com.zeroprojects.backgammon.utils.ThemeHelper

class BoardView(context: Context, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {

    var middleDivider: LinearLayout? = null
    var diceOne: DiceView? = null
    var diceTwo: DiceView? = null

    init {
        background = getDrawableBackground()
        addDivider()
        invalidate()
    }

    private fun getDrawableBackground(): GradientDrawable {
        val gradientDrawable = GradientDrawable()
        gradientDrawable.shape = GradientDrawable.RECTANGLE
        gradientDrawable.setColor(ContextCompat.getColor(context, R.color.white))
        gradientDrawable.setStroke(
            DimensionUtils.dpToPx(50f),
            ContextCompat.getColor(context, R.color.wall_color)
        )
        return gradientDrawable
    }

    private fun addDivider() {
        middleDivider = LinearLayout(context).apply {
            id = View.generateViewId()
            val gradientDrawable = GradientDrawable()
            gradientDrawable.shape = GradientDrawable.RECTANGLE
            gradientDrawable.setColor(ContextCompat.getColor(context, R.color.wall_color))
            background = gradientDrawable
            val layoutParams = LayoutParams(
                LayoutParams.MATCH_PARENT,
                DimensionUtils.dpToPx(200f)
            ).apply {
                gravity = Gravity.CENTER  // Center vertically within the LinearLayout
            }
            layoutParams.addRule(CENTER_IN_PARENT)
            this.layoutParams = layoutParams
        }
        addDiceOne()
        addDiceTwo()
        addView(middleDivider)
        middleDivider?.orientation = LinearLayout.HORIZONTAL

    }

    private fun addDiceOne() {
        diceOne = DiceView(context).apply {
            id = View.generateViewId()

            val layoutParams = LinearLayout.LayoutParams(
                DimensionUtils.dpToPx(100f),
                DimensionUtils.dpToPx(100f)
            )
            setDiceSize(100f)
            this.layoutParams = layoutParams
        }
        diceOne?.roll()
        middleDivider?.addView(diceOne)
    }


    private fun addDiceTwo() {
        diceTwo = DiceView(context).apply {
            id = View.generateViewId()
            val layoutParams = LinearLayout.LayoutParams(
                DimensionUtils.dpToPx(100f),
                DimensionUtils.dpToPx(100f)
            )
            this.layoutParams = layoutParams
            setDiceSize(100f)
        }
        diceTwo?.roll()
        middleDivider?.addView(diceTwo)

    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // Draw your lines here using the linePaint object
    }
}