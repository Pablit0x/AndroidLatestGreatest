package com.ps.latestgreatestplayground.presentation.messenger

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MessengerViewModel : ViewModel() {

    private val _messengerUiState = MutableStateFlow(MessengerUiState())
    val messengerUiState = _messengerUiState.asStateFlow()

    fun onAction(action: MessengerAction) {
        when (action) {
            MessengerAction.MessageSent -> _messengerUiState.update {
                it.copy(
                    messages = it.messages + Message(
                        text = it.currentMessageValue,
                        formattedTime = LocalDateTime.now()
                            .format(DateTimeFormatter.ofPattern("HH:mm:ss"))
                    ), currentMessageValue = ""
                )
            }

            is MessengerAction.MessageValueChanged -> _messengerUiState.update {
                it.copy(
                    currentMessageValue = action.newValue
                )
            }
        }
    }
}