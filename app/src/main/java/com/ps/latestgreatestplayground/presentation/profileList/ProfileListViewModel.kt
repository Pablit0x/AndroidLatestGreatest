package com.ps.latestgreatestplayground.presentation.profileList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ps.latestgreatestplayground.R
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileListViewModel : ViewModel() {

    private val _profileListEvent = MutableSharedFlow<ProfileListEvent>()
    val profileListEvent = _profileListEvent.asSharedFlow()

    private val _profileListUiState = MutableStateFlow(ProfileListUiState())
    val profileListUiState = _profileListUiState.asStateFlow()

    init {
        populateProfileList()
    }

    private fun populateProfileList() {
        _profileListUiState.update {
            it.copy(
                profiles = listOf(Profile(name = "Bojack Horseman", imageResourceId = R.drawable.bojack))
            )
        }
    }

    fun onAction(profileListAction: ProfileListAction) {
        when (profileListAction) {
            is ProfileListAction.NavigateToProfileDetail -> {}
        }
    }

    private fun emitEvent(event: ProfileListEvent) = viewModelScope.launch { _profileListEvent.emit(value = event) }
}