package com.jamitlabs.starfacepresentation.viewmodel

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import com.jamitlabs.starfacepresentation.R
import com.jamitlabs.starfacepresentation.util.livedata.Event

abstract class BaseViewModel : ViewModel() {

    val events = MutableLiveData<Event<CommonAction>>()

    private fun postAction(commonAction: CommonAction) = events.postValue(Event(commonAction))

    fun navigateTo(
            @IdRes navigationTargetId: Int,
            clearBackStack: Boolean = false,
            args: Bundle? = null,
            navOptions: NavOptions? = null,
            extras: Navigator.Extras? = null
    ) = postAction(NavigateTo(
            navigationTargetId = navigationTargetId,
            clearBackStack = clearBackStack,
            args = args,
            navOptions = navOptions,
            extras = extras
    ))

    fun showErrorSnackBar(message: String) = showSnackbar(ShowSnackbar(
            message = message,
            colorRes = R.color.colorNegative
    ))

    private fun showSnackbar(snackbarEvent: ShowSnackbar) = postAction(snackbarEvent)
}

