package com.bakharaalief.hulaapp.core.data.source.remote

import android.util.Log
import com.bakharaalief.hulaapp.core.data.source.remote.response.MovieItem
import com.bakharaalief.hulaapp.core.data.source.remote.retorift.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource private constructor(private val apiService: ApiService) {

    suspend fun getAllTourism(apiKey: String): Flow<ApiResponse<List<MovieItem>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getListMovie(apiKey)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }
}