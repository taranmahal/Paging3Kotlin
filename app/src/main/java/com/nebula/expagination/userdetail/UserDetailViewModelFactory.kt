package com.nebula.expagination.userdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nebula.expagination.userlist.data.Result

class UserDetailViewModelFactory constructor(private val user: Result) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(UserDetailViewModel::class.java)) {
            UserDetailViewModel(this.user) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}