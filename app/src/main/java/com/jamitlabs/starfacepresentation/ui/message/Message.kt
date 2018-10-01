package com.jamitlabs.starfacepresentation.ui.message

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Message(
        val text: String,
        val messageType: MessageType,
        val messageId: String = UUID.randomUUID().toString()
) : Parcelable {

    enum class MessageType {
        RECEIVED, SENT
    }
}

