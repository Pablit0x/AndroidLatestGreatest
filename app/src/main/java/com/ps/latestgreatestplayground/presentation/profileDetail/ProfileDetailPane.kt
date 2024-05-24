package com.ps.latestgreatestplayground.presentation.profileDetail


import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.ps.latestgreatestplayground.presentation.document.DocumentDestination
import com.ps.latestgreatestplayground.presentation.messenger.MessengerPane
import com.ps.latestgreatestplayground.presentation.profileList.Profile

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun SharedTransitionScope.ProfileDetailPane(
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
    profile: Profile,
    navController: NavController,
) {
    val navigator = rememberListDetailPaneScaffoldNavigator()


    ListDetailPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        listPane = {
            MailingListPane(
                modifier = modifier,
                profile = profile,
                animatedVisibilityScope = animatedVisibilityScope,
                onMessageClicked = {
                    navigator.navigateTo(
                        pane = ListDetailPaneScaffoldRole.Detail,
                    )
                },
                onDocumentClicked = {
                    navController.navigate(DocumentDestination)
                },
                threePaneScaffoldScope = this
            )
        },
        detailPane = { MessengerPane(modifier = modifier) },
    )

    BackHandler(navigator.canNavigateBack()) {
        navigator.navigateBack()
    }

}