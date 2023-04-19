package com.nebula.expagination.userlist.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Registered(
    val age: Int,
    val date: String
) : Parcelable