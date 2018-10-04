package com.jamitlabs.starfacepresentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Message(
        val text: String,
        val messageType: MessageType
) : Parcelable {

    enum class MessageType {
        RECEIVED, SENT
    }
}

