package com.jamitlabs.starfacepresentation.di

import com.jamitlabs.starfacepresentation.StarfacePresentationApplication
import com.jamitlabs.starfacepresentation.repository.RemoteStarfaceRepository
import com.jamitlabs.starfacepresentation.repository.StarfaceRepository
import com.jamitlabs.starfacepresentation.ui.message.MessageViewModel
import com.jamitlabs.starfacepresentation.util.resources.AndroidResourceProvider
import com.jamitlabs.starfacepresentation.util.resources.ResourceProvider
import com.jamitlabs.starfacepresentation.util.rxjava.AndroidSchedulerProvider
import com.jamitlabs.starfacepresentation.util.rxjava.SchedulerProvider
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {
    single<SchedulerProvider> { AndroidSchedulerProvider() }
    single<StarfaceRepository> { RemoteStarfaceRepository() }
    single<ResourceProvider> { AndroidResourceProvider(StarfacePresentationApplication.INSTANCE) }
}

val viewModelModule = module {
    viewModel { MessageViewModel(get(), get(), get()) }
}

