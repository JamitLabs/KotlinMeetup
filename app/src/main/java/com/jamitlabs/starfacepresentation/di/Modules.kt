package com.jamitlabs.starfacepresentation.di

import android.app.Application
import com.jamitlabs.starfacepresentation.StarfacePresentationApplication
import com.jamitlabs.starfacepresentation.repository.RemoteStarfaceRepository
import com.jamitlabs.starfacepresentation.repository.StarfaceRepository
import com.jamitlabs.starfacepresentation.ui.message.detail.MessageDetailViewModel
import com.jamitlabs.starfacepresentation.ui.message.overview.MessageViewModel
import com.jamitlabs.starfacepresentation.util.resources.AndroidResourceProvider
import com.jamitlabs.starfacepresentation.util.resources.ResourceProvider
import com.jamitlabs.starfacepresentation.util.rxjava.AndroidSchedulerProvider
import com.jamitlabs.starfacepresentation.util.rxjava.SchedulerProvider
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val appModule = module {
    single<Application> { StarfacePresentationApplication.INSTANCE }
    single<SchedulerProvider> { AndroidSchedulerProvider() }
    single<StarfaceRepository> { RemoteStarfaceRepository() }
    single<ResourceProvider> { AndroidResourceProvider(get() ) }
}

val viewModelModule = module {
    viewModel { MessageViewModel(get(), get(), get()) }
    viewModel { MessageDetailViewModel() }
}
