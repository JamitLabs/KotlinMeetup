package com.jamitlabs.starfacepresentation.ui.message.overview

import com.jamitlabs.starfacepresentation.model.Message

typealias OnMessageClickListener = (message: Message) -> Unit

class MessageItemViewModel(
        val message: Message,
        val onMessageClickedListener: OnMessageClickListener
) {
    fun onMessageClicked() = onMessageClickedListener(message)
}

