package com.ps.latestgreatestplayground.presentation.profileList

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data object ProfileListDestination

@Serializable
@Parcelize
data class Profile(val name: String, val imageResourceId: Int): Parcelable

val ProfileNavType = object: NavType<Profile>(isNullableAllowed = false) {
    override fun put(bundle: Bundle, key: String, value: Profile) {
        bundle.putParcelable(key, value)
    }

    override fun parseValue(value: String): Profile {
        return Json.decodeFromString<Profile>(value)
    }

    override fun get(bundle: Bundle, key: String): Profile? {
        return bundle.getParcelable(key, Profile::class.java) as Profile
    }

    override fun serializeAsValue(value: Profile): String {
        return Json.encodeToString(value = value)
    }
}

data class ProfileListUiState(
    val profiles: List<Profile> = emptyList()
)
