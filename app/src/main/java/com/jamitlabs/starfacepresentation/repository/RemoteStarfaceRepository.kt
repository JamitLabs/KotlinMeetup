package com.jamitlabs.starfacepresentation.repository

import io.reactivex.Single
import java.util.concurrent.TimeUnit

class RemoteStarfaceRepository : StarfaceRepository {

    override fun sendMessage(message: String) = Single.just(message.reversed()).delay(5L, TimeUnit.SECONDS)
}
