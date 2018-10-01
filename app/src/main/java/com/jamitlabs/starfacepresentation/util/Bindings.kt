package com.jamitlabs.starfacepresentation.util

import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jamitlabs.starfacepresentation.R
import com.jamitlabs.starfacepresentation.ui.message.Message
import com.jamitlabs.starfacepresentation.util.livedata.Event

@BindingAdapter("scrollToBottom")
fun scrollToBottom(recyclerView: RecyclerView, scrollToBottom: Event<Boolean>?) {
    scrollToBottom?.getContentIfNotHandled()?.let {
        if (it) {
            recyclerView.adapter?.let {
                if (it.itemCount != 0) {
                    recyclerView.smoothScrollToPosition(it.itemCount - 1)
                }
            }
        }
    }
}

@BindingAdapter("messageColor")
fun setMessageColor(cardView: CardView, message: Message) {
    val colorResId = if (message.messageType == Message.MessageType.RECEIVED) {
        R.color.colorPrimaryDark
    } else {
        R.color.colorNegative
    }

    cardView.setCardBackgroundColor(ContextCompat.getColor(cardView.context, colorResId))
}

