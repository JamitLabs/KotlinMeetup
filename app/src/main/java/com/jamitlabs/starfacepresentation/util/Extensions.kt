package com.jamitlabs.starfacepresentation.util

import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.jamitlabs.starfacepresentation.R
import com.jamitlabs.starfacepresentation.viewmodel.NavigateTo
import com.jamitlabs.starfacepresentation.viewmodel.ShowSnackbar
import timber.log.Timber

fun View.snack(showSnackbarEvent: ShowSnackbar): Snackbar = with(showSnackbarEvent) {
    val whiteSpan = ForegroundColorSpan(ContextCompat.getColor(context, R.color.white))
    val snackbarText = SpannableStringBuilder(message)
    snackbarText.setSpan(whiteSpan, 0, snackbarText.length, Spanned.SPAN_INCLUSIVE_INCLUSIVE)

    val snackbar = Snackbar.make(this@snack, snackbarText, length)
    snackbar.view.setBackgroundColor(ContextCompat.getColor(context, colorRes))

    snackbar.show()

    return snackbar
}

fun Fragment.navigateTo(navigateToEvent: NavigateTo) {
    view?.findNavController()?.navigateTo(navigateToEvent)
            ?: Timber.d("Could not find navigation controller for $this")
}

fun NavController.navigateTo(navigateToEvent: NavigateTo) {
    var navOptions = navigateToEvent.navOptions

    if (navigateToEvent.clearBackStack) {
        navOptions = navOptions.setClearBackStack(graph.id)
    }

    navigate(navigateToEvent.navigationTargetId, navigateToEvent.args, navOptions)
}

private fun NavOptions?.setClearBackStack(graphId: Int): NavOptions = if (this == null) {
    NavOptions.Builder()
            .setPopUpTo(graphId, true)
            .build()
} else {
    NavOptions.Builder()
            .setEnterAnim(enterAnim)
            .setPopUpTo(graphId, true)
            .setExitAnim(exitAnim)
            .setPopEnterAnim(popEnterAnim)
            .setPopExitAnim(popExitAnim)
            .setLaunchSingleTop(shouldLaunchSingleTop())
            .build()

}
