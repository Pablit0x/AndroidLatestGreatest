package com.ps.latestgreatestplayground.presentation.messenger

import kotlin.random.Random


data class MessengerUiState(
    val messages: List<Message> = emptyList(),
    val currentMessageValue: String = "",
)

data class Message(
    val text: String,
    val formattedTime: String,
    val isReceived: Boolean = Random.nextBoolean(),
)


sealed interface MessengerAction {
    data class MessageValueChanged(val newValue: String) : MessengerAction
    data object MessageSent : MessengerAction
}