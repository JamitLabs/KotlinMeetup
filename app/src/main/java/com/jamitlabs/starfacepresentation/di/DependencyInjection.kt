package com.jamitlabs.starfacepresentation.di

import com.jamitlabs.starfacepresentation.StarfacePresentationApplication
import com.jamitlabs.starfacepresentation.repository.RemoteStarfaceRepository
import com.jamitlabs.starfacepresentation.repository.StarfaceRepository
import com.jamitlabs.starfacepresentation.util.resources.AndroidResourceProvider
import com.jamitlabs.starfacepresentation.util.resources.ResourceProvider
import com.jamitlabs.starfacepresentation.util.rxjava.AndroidSchedulerProvider
import com.jamitlabs.starfacepresentation.util.rxjava.SchedulerProvider

//Should probably be koin, dagger 2 or something like that, but I think that would be out of the scope of the presentation

object DependencyInjection {

    val starfaceRepository: StarfaceRepository by lazy { RemoteStarfaceRepository() }

    val schedulerProvider: SchedulerProvider by lazy { AndroidSchedulerProvider() }

    val resoureProvider: ResourceProvider by lazy { AndroidResourceProvider(StarfacePresentationApplication.INSTANCE) }

}
