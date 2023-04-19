package com.nebula.expagination.userlist.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Id(
    val name: String,
    val value: String
) : Parcelable