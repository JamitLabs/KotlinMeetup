package com.jamitlabs.starfacepresentation.ui.message

import androidx.databinding.Bindable
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import com.jamitlabs.starfacepresentation.R
import com.jamitlabs.starfacepresentation.repository.StarfaceRepository
import com.jamitlabs.starfacepresentation.util.rxjava.SchedulerProvider
import com.jamitlabs.starfacepresentation.util.rxjava.with
import com.jamitlabs.starfacepresentation.viewmodel.BaseViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import me.tatarka.bindingcollectionadapter2.BR
import me.tatarka.bindingcollectionadapter2.ItemBinding
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass
import timber.log.Timber

class MessageViewModel(
        private val starfaceRepository: StarfaceRepository,
        private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val messageText = ObservableField<String>()

    @Bindable
    var scrollToBottom = true
        private set(value) {
            field = value
            notifyPropertyChanged(BR.scrollToBottom)
        }

    val messages = ObservableArrayList<Message>()
    val bindings = object : OnItemBindClass<Message>() {
        override fun onItemBind(itemBinding: ItemBinding<*>, position: Int, item: Message) {
            itemBinding.set(
                    BR.viewModel,
                    if (item.messageType == Message.MessageType.SENT) {
                        R.layout.item_send_message
                    } else {
                        R.layout.item_received_message
                    }
            )
        }
    }

    private fun addMessage(message: String, messageType: Message.MessageType) {
        messages.add(Message(message, messageType))
        scrollToBottom()
    }

    fun scrollToBottom() {
        scrollToBottom = true
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
                        onError = Timber::e
                )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
