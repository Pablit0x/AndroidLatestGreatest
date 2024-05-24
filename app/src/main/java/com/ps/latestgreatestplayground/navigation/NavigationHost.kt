package com.ps.latestgreatestplayground.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.ps.latestgreatestplayground.presentation.document.DocumentDestination
import com.ps.latestgreatestplayground.presentation.document.DocumentScreen
import com.ps.latestgreatestplayground.presentation.home.HomeDestination
import com.ps.latestgreatestplayground.presentation.home.HomePane
import com.ps.latestgreatestplayground.presentation.profileDetail.ProfileDetailDestination
import com.ps.latestgreatestplayground.presentation.profileDetail.ProfileDetailPane
import com.ps.latestgreatestplayground.presentation.profileList.Profile
import com.ps.latestgreatestplayground.presentation.profileList.ProfileListDestination
import com.ps.latestgreatestplayground.presentation.profileList.ProfileListPane
import com.ps.latestgreatestplayground.presentation.profileList.ProfileNavType
import kotlin.reflect.typeOf

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NavigationHost(modifier: Modifier = Modifier, navController: NavHostController) {

    SharedTransitionLayout {
        NavHost(
            navController = navController, startDestination = HomeDestination
        ) {
            composable<HomeDestination> {
                HomePane(modifier = modifier, navController = navController)
            }

            composable<ProfileListDestination> {
                ProfileListPane(
                    modifier = modifier,
                    navController = navController,
                    animatedVisibilityScope = this
                )
            }

            composable<ProfileDetailDestination>(
                typeMap = mapOf(typeOf<Profile>() to ProfileNavType)
            ) {
                val navArgs = it.toRoute<ProfileDetailDestination>()
                ProfileDetailPane(
                    modifier = modifier,
                    profile = navArgs.profile,
                    animatedVisibilityScope = this,
                    navController = navController,
                )
            }

            composable<DocumentDestination> {
                DocumentScreen(modifier = modifier)
            }
        }

    }

}