package com.jamitlabs.starfacepresentation.viewmodel

import android.os.Bundle
import androidx.annotation.ColorRes
import androidx.annotation.IdRes
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import com.google.android.material.snackbar.Snackbar
import com.jamitlabs.starfacepresentation.R

sealed class CommonAction

data class ShowSnackbar(
        val message: String,
        @ColorRes val colorRes: Int = R.color.colorNegative,
        val length: Int = Snackbar.LENGTH_LONG
) : CommonAction()

data class NavigateTo(
        @IdRes val navigationTargetId: Int,
        val clearBackStack: Boolean = false,
        val args: Bundle? = null,
        val navOptions: NavOptions? = null,
        val extras: Navigator.Extras? = null
) : CommonAction()


