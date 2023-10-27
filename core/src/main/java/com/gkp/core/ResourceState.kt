package com.gkp.core

sealed class ResourceState<out T> {
    object Loading : ResourceState<Nothing>()
    data class Success<T>(val data: T) : ResourceState<T>()
    data class Error(val message: String? = null) : ResourceState<Nothing>()
}
