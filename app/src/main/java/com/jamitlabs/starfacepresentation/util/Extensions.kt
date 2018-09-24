package com.jamitlabs.starfacepresentation.util

import android.view.View
import de.mateware.snacky.Snacky

fun View.snackError(message: CharSequence) {

    Snacky.builder().setView(this).setText(message).error().show()

    return
}

