package com.ps.latestgreatestplayground.presentation.home

import kotlinx.serialization.Serializable

@Serializable
data object HomeDestination

sealed interface HomeAction {
    data object NavigateToProfile: HomeAction
    data object NavigateToList: HomeAction
}