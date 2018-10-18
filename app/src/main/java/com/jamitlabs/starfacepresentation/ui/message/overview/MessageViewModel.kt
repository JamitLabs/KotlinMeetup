package com.jamitlabs.starfacepresentation.ui.message.overview

import android.os.Bundle
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import com.jamitlabs.starfacepresentation.R
import com.jamitlabs.starfacepresentation.base.BaseViewModel
import com.jamitlabs.starfacepresentation.model.Message
import com.jamitlabs.starfacepresentation.repository.StarfaceRepository
import com.jamitlabs.starfacepresentation.ui.message.detail.MessageDetailFragment
import com.jamitlabs.starfacepresentation.util.livedata.Event
import com.jamitlabs.starfacepresentation.util.resources.ResourceProvider
import com.jamitlabs.starfacepresentation.util.rxjava.SchedulerProvider
import com.jamitlabs.starfacepresentation.util.rxjava.with
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import me.tatarka.bindingcollectionadapter2.BR
import me.tatarka.bindingcollectionadapter2.ItemBinding
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass

class MessageViewModel(
        private val starfaceRepository: StarfaceRepository,
        private val schedulerProvider: SchedulerProvider,
        private val resourceProvider: ResourceProvider
) : BaseViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val messageText = ObservableField<String>()
    var scrollToBottom = ObservableField<Event<Boolean>>()

    val messages = ObservableArrayList<MessageItemViewModel>()
    val bindings = object : OnItemBindClass<MessageItemViewModel>() {
        override fun onItemBind(itemBinding: ItemBinding<*>, position: Int, item: MessageItemViewModel) {
            itemBinding.set(
                    BR.viewModel,
                    if (item.message.messageType == Message.MessageType.SENT) {
                        R.layout.item_send_message
                    } else {
                        R.layout.item_received_message
                    }
            )
        }
    }

    private fun onMessageClicked(message: Message) {
        navigateTo(
                navigationTargetId = R.id.action_mainFragment_to_messageDetailFragment,
                args = Bundle().apply {
                    putParcelable(MessageDetailFragment.KEY_MESSAGE, message)
                }
        )
    }

    private fun addMessage(message: String, messageType: Message.MessageType) {
        messages.add(MessageItemViewModel(Message(message, messageType), this::onMessageClicked))
        scrollToBottom()
    }

    fun scrollToBottom() {
        scrollToBottom.set(Event(true))
    }

    fun onSendMessage() {
        val messageToSent = messageText.get()?.trim()

        if (messageToSent != null && !messageToSent.isBlank()) {
            sendMessage(messageToSent)
        }

        messageText.set(null)
    }

    private fun sendMessage(message: String) {
        compositeDisposable += starfaceRepository
                .sendMessage(message)
                .with(schedulerProvider)
                .doOnSubscribe { addMessage(message, Message.MessageType.SENT) }
                .subscribeBy(
                        onSuccess = { response -> addMessage(response, Message.MessageType.RECEIVED) },
                        onError = { showErrorSnackBar(resourceProvider.getString(R.string.something_went_wrong)) }
                )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
