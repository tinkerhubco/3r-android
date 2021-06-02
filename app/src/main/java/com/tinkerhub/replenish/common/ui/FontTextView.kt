package com.tinkerhub.replenish.common.ui

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.res.ResourcesCompat
import com.tinkerhub.replenish.R


class FontTextView(
    context: Context,
    attrs: AttributeSet
) : AppCompatTextView(context, attrs) {
    
    private val styledAttributes =
        context.obtainStyledAttributes(attrs, R.styleable.FontTextView, 0, 0)
    
    init {
        val fontAttribute = styledAttributes.getResourceId(
            R.styleable.FontTextView_customTypeface,
            R.font.archivo
        )
        val font = ResourcesCompat.getFont(context, fontAttribute)
        setTypeface(font, Typeface.NORMAL)
    }
}