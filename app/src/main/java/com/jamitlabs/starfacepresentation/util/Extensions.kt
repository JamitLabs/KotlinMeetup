package com.jamitlabs.starfacepresentation.util

import androidx.lifecycle.LiveData
import com.jamitlabs.starfacepresentation.util.test.TestObserver

fun <T> LiveData<T>.testObserver() = TestObserver<T>().also {
    observeForever(it)
}
