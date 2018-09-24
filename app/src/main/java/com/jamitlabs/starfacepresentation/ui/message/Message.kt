package com.jamitlabs.starfacepresentation.ui.message

data class Message(
        val message: String,
        val messageType: MessageType
) {
    enum class MessageType {
        RECEIVED, SENT
    }
}

