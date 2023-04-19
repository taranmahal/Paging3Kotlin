package com.nebula.expagination.userlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.nebula.expagination.Repository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import com.nebula.expagination.userlist.data.Result

class UserViewModel constructor(private val repository: Repository) : ViewModel() {

    val errorMessage = MutableLiveData<String>()
 //   val userList = MutableLiveData/*<UserResult>()*/<List<Result>>()
    var job: Job? = null
  /*  private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }*/
    //val loading = MutableLiveData<Boolean>()

    /*fun getUsers() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = mainRepository.getUsers()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    userList.postValue(response.body()?.results)
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }

    }*/

    fun getUsers(): Flow<PagingData<Result>> {

        return repository.letUsersFlow()
            //.map { it.map { it } }
            .cachedIn(viewModelScope)
    }

    private fun onError(message: String) {
        errorMessage.value = message
      //  loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}