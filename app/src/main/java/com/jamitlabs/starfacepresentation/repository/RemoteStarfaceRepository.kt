package com.jamitlabs.starfacepresentation.repository

import io.reactivex.Single
import java.util.concurrent.TimeUnit

class RemoteStarfaceRepository : StarfaceRepository {

    companion object {
        const val RESPONSE_DELAY_IN_SECONDS = 5L
        const val ERROR_TRIGGER = "error"
    }

    override fun sendMessage(message: String): Single<String> = if (message.toLowerCase() == ERROR_TRIGGER) {
        Single.error(IllegalStateException("Error"))
    } else {
        Single.just(message.reversed()).delay(RESPONSE_DELAY_IN_SECONDS, TimeUnit.SECONDS)
    }

}
