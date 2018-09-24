package com.jamitlabs.starfacepresentation.util.lifecycle

import androidx.lifecycle.Observer

class CachingObserver<T>: Observer<T?> {

    var value: T? = null

    override fun onChanged(t: T?) {
        value = t
    }
}
