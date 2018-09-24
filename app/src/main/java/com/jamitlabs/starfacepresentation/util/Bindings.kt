package com.jamitlabs.starfacepresentation.util

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("scrollToBottom")
fun scrollToBottom(recyclerView: RecyclerView, scrollToBottom: Boolean) {
    if (scrollToBottom) {
        recyclerView.adapter?.let {
            if (it.itemCount != 0) {
                recyclerView.smoothScrollToPosition(it.itemCount - 1)
            }
        }
    }
}



