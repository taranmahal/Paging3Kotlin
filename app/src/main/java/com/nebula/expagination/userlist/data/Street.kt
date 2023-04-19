package com.nebula.expagination.userlist.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Street(
    val name: String,
    val number: Int
) : Parcelable