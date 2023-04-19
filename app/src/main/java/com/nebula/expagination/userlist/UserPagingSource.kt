package com.nebula.expagination.userlist

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nebula.expagination.api.RetrofitService
import com.nebula.expagination.userlist.data.Result
import retrofit2.HttpException
import java.io.IOException


class UserPagingSource(private val retrofitService: RetrofitService) : PagingSource<Int, Result>() {

    private val DEFAULT_PAGE_INDEX = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {

        val page = params.key ?: DEFAULT_PAGE_INDEX
        return try {
            val response = retrofitService.getUsers(page, params.loadSize).body()!!.results
            LoadResult.Page(
                response, prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}