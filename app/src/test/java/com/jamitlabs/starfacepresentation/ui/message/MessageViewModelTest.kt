package com.jamitlabs.starfacepresentation.ui.message

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.jamitlabs.starfacepresentation.R
import com.jamitlabs.starfacepresentation.repository.StarfaceRepository
import com.jamitlabs.starfacepresentation.util.livedata.Event
import com.jamitlabs.starfacepresentation.util.resources.ResourceProvider
import com.jamitlabs.starfacepresentation.util.rxjava.TestingSchedulerProvider
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Single
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.TimeUnit

class MessageViewModelTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `Test send message failure`() {
        val sentMessageText = "Foo"
        val errorMessage = "Bar"

        val mockRepository = mock<StarfaceRepository> {
            on { sendMessage(sentMessageText) } doReturn  Single.error(RuntimeException(sentMessageText))
        }

        val resourceProvider = mock<ResourceProvider> {
            on { getString(R.string.something_went_wrong) } doReturn errorMessage
        }

        val schedulerProvider = TestingSchedulerProvider()

        val mainViewModel = MessageViewModel(mockRepository, schedulerProvider, resourceProvider)

        val observer = mock<Observer<Event<MessageEvent>>>()

        mainViewModel.events.observeForever(observer)

        mainViewModel.messageText.set(sentMessageText)

        mainViewModel.onSendMessage()

        TestingSchedulerProvider.TEST_SCHEDULER.advanceTimeBy(1, TimeUnit.SECONDS)

        verify(observer).onChanged(Event(ShowError(errorMessage)))
    }

    @Test
    fun `Test send and receive message`() {
        val sentMessageText = "Foo"
        val receivedMessageText = "Bar"

        val mockRepository = mock<StarfaceRepository> {
            on { sendMessage(sentMessageText) } doReturn Single.just(receivedMessageText).delay(
                    10,
                    TimeUnit.SECONDS,
                    TestingSchedulerProvider.TEST_SCHEDULER
            )
        }

        val resourceProvider = mock<ResourceProvider> {}
        val schedulerProvider = TestingSchedulerProvider()
        val mainViewModel = MessageViewModel(mockRepository, schedulerProvider, resourceProvider)

        assertTrue(
                "Displayed messages should be empty",
                mainViewModel.messages.isEmpty()
        )

        mainViewModel.messageText.set(sentMessageText)
        mainViewModel.onSendMessage()

        assertTrue(
                "Text input should be cleared after sending a message",
                mainViewModel.messageText.get() == null
        )

        TestingSchedulerProvider.TEST_SCHEDULER.advanceTimeBy(1, TimeUnit.SECONDS)

        assertTrue(
                "Displayed messages should only contain the sent message",
                mainViewModel.messages == listOf(
                        Message(message = sentMessageText, messageType = Message.MessageType.SENT)
                )
        )

        TestingSchedulerProvider.TEST_SCHEDULER.advanceTimeBy(10, TimeUnit.SECONDS)

        assertTrue(
                "Displayed messages should contain the sent and the received message",
                mainViewModel.messages == listOf(
                        Message(message = sentMessageText, messageType = Message.MessageType.SENT),
                        Message(message = receivedMessageText, messageType = Message.MessageType.RECEIVED)
                ))

    }
}