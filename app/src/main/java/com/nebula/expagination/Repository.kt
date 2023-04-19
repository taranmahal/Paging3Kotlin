package com.nebula.expagination

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.nebula.expagination.api.RetrofitService
import com.nebula.expagination.userlist.UserPagingSource
import com.nebula.expagination.userlist.data.Result
import kotlinx.coroutines.flow.Flow

class Repository constructor(private val retrofitService: RetrofitService) {

    private val DEFAULT_PAGE_SIZE = 10

  //  suspend fun getUsers() = retrofitService.getUsers(10, 1)

    fun letUsersFlow(pagingConfig: PagingConfig = getDefaultPageConfig()): Flow<PagingData<Result>> {

        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { UserPagingSource(retrofitService) }
        ).flow

    }

    private fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = false)
    }
}