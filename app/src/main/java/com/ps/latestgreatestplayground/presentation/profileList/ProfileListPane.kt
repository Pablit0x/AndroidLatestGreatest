package com.ps.latestgreatestplayground.presentation.profileList

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ps.latestgreatestplayground.presentation.profileDetail.ProfileDetailDestination

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.ProfileListPane(
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
    navController: NavController,
) {
    val viewModel = viewModel<ProfileListViewModel>()
    val uiState by viewModel.profileListUiState.collectAsState()

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(space = 16.dp),
        contentPadding = PaddingValues(all = 16.dp)
    ) {
        items(uiState.profiles) { profile ->
            ProfileCard(animatedVisibilityScope = animatedVisibilityScope,
                profile = profile,
                onClick = {
                    navController.navigate(
                        ProfileDetailDestination(
                            profile = profile
                        )
                    )
                })
        }
    }
}