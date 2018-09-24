package com.jamitlabs.starfacepresentation.util.resources

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

class AndroidResourceProvider constructor(private val context: Context) : ResourceProvider {
    override fun getDrawable(resId: Int): Drawable? = ContextCompat.getDrawable(context, resId)
    override fun getColor(resId: Int): Int = ContextCompat.getColor(context, resId)
    override fun getString(@StringRes resId: Int): String = context.getString(resId)
    override fun getString(@StringRes resId: Int, vararg args: Any?): String = context.getString(resId, *args)
    override fun getDimensionPixelSize(resId: Int) = context.resources.getDimensionPixelSize(resId)
    override fun getInteger(resId: Int) = context.resources.getInteger(resId)
}

