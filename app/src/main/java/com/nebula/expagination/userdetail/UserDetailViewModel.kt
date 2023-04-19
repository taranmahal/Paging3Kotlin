package com.nebula.expagination.userdetail

import androidx.lifecycle.ViewModel
import com.nebula.expagination.userlist.data.Result

class UserDetailViewModel constructor(val user: Result) : ViewModel() {

    val name: String
    get() = "${user.name.title} ${user.name.first} ${user.name.last}, ${user.dob.age} years old ${user.gender}"

    val url: String
        get() = user.picture.medium

    val city: String
        get() = "${user.location.city}, ${user.location.country}"

    val email: String
        get() = user.email?:"NA"

    val phone: String
        get() = user.phone?:"NA"

    val followers: String
        get() = "367"/*(200..999).random()*/

    val following: String
        get() = "214"/*(50..499).random()*/

  /*  val gender: String
        get() = user.gender?:"NA"*/

}