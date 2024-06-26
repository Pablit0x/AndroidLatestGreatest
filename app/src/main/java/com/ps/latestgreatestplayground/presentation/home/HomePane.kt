package com.ps.latestgreatestplayground.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.ps.latestgreatestplayground.R
import com.ps.latestgreatestplayground.presentation.profileList.ProfileListDestination

@Composable
fun HomePane(modifier: Modifier = Modifier, navController: NavController) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { navController.navigate(ProfileListDestination) }) {
            Text(text = stringResource(R.string.navigate_to_profile_list))
        }
    }
}