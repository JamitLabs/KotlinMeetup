package com.jamitlabs.starfacepresentation.util.resources

import android.graphics.drawable.Drawable
import androidx.annotation.StringRes

interface ResourceProvider {

    fun getString(@StringRes resId: Int): String
    fun getString(@StringRes resId: Int, vararg args: Any?): String
    fun getColor(resId: Int): Int
    fun getDrawable(resId: Int): Drawable?
    fun getDimensionPixelSize(resId: Int): Int
    fun getInteger(resId: Int): Int
}

