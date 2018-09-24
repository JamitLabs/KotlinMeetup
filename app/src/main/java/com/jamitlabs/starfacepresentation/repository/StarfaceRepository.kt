package com.jamitlabs.starfacepresentation.repository

import io.reactivex.Single

interface StarfaceRepository {

    //TODO Check what this app is actually doing
    fun sendMessage(message: String): Single<String>
}
