package com.ps.latestgreatestplayground.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _uiEvent = MutableSharedFlow<HomeEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    fun onAction(homeAction: HomeAction) {
        when (homeAction) {
            HomeAction.NavigateToList -> emitEvent(event = HomeEvent.NavigateToList)
            HomeAction.NavigateToProfile -> emitEvent(event = HomeEvent.NavigateToList)
        }
    }

    private fun emitEvent(event: HomeEvent) = viewModelScope.launch { _uiEvent.emit(value = event) }
}