package com.ps.latestgreatestplayground.presentation.home

import kotlinx.serialization.Serializable

@Serializable
data object HomeDestination

sealed interface HomeEvent {
    data object NavigateToProfile: HomeEvent
    data object NavigateToList: HomeEvent
}

sealed interface HomeAction {
    data object NavigateToProfile: HomeAction
    data object NavigateToList: HomeAction
}