package com.gkp.core

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
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

@RequiresApi(Build.VERSION_CODES.O)
fun formatDateString(instantString: String): String {
    println(" ARM_date : $instantString")
    val instant = Instant.parse(instantString)
    val localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    return formatter.format(localDateTime)
}
