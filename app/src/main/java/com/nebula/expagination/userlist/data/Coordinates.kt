package com.nebula.expagination.userlist.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Coordinates(
    val latitude: String,
    val longitude: String
) : Parcelable