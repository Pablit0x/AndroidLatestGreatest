package com.ps.latestgreatestplayground.presentation.profileDetail

import com.ps.latestgreatestplayground.presentation.profileList.Profile
import kotlinx.serialization.Serializable

@Serializable
data class ProfileDetailDestination(val profile: Profile)
