package com.jamitlabs.starfacepresentation.ui.message

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jamitlabs.starfacepresentation.di.DependencyInjection
import com.jamitlabs.starfacepresentation.repository.StarfaceRepository
import com.jamitlabs.starfacepresentation.util.resources.ResourceProvider
import com.jamitlabs.starfacepresentation.util.rxjava.SchedulerProvider

class MessageViewModelFactory(
        private val starfaceRepository: StarfaceRepository = DependencyInjection.starfaceRepository,
        private val schedulerProvider: SchedulerProvider = DependencyInjection.schedulerProvider,
        private val resourceProvider: ResourceProvider = DependencyInjection.resoureProvider
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MessageViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MessageViewModel(
                    starfaceRepository,
                    schedulerProvider,
                    resourceProvider
            ) as T
        } else {
            throw IllegalArgumentException("Unknown model class $modelClass")
        }
    }
}
