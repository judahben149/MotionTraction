package com.judahben149.motiontraction.utils

sealed class OperationResult<out T> {
    data class Success<out T>(val data: T) : OperationResult<T>()
    data class Error(val throwable: Throwable) : OperationResult<Nothing>()
}
