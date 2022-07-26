package com.sparjapati.core.utils

typealias SimpleResource = Resource<Unit>

sealed class Resource<T> private constructor(val data: T? = null, val message: TextUtil? = null) {
    class Loading<T>(data: T? = null) : Resource<T>(data = data)
    class Success<T>(data: T?) : Resource<T>(data = data, message = null)
    class Error<T>(message: TextUtil, data: T? = null) : Resource<T>(data = data, message = message)
}
