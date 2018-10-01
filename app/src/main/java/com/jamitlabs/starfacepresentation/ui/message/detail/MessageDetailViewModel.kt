package com.jamitlabs.starfacepresentation.ui.message.detail

import androidx.databinding.ObservableField
import com.jamitlabs.starfacepresentation.ui.message.Message
import com.jamitlabs.starfacepresentation.viewmodel.BaseViewModel

class MessageDetailViewModel : BaseViewModel() {

    val message = ObservableField<Message>()

    fun displayMessage(message: Message) {
        this.message.set(message)
    }
}

