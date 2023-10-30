package com.gkp.core

import android.util.Log
import java.util.concurrent.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

fun <T, R> safeApiCall(
    mapper: (T) -> R,
    apiCall: suspend () -> T
): Flow<ResourceState<R>> = flow {
    emit(ResourceState.Loading)
    emit(ResourceState.Success(mapper(apiCall())))
}.catch { cause ->
    if (cause is CancellationException) throw cause
    emit(ResourceState.Error(cause.message))
    Log.d("ApiCallError", " error ${cause.message}")
}
