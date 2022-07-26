package com.sparjapati.core.utils

import android.content.Context

sealed class TextUtil {
    data class StringResource(val resId: Int) : TextUtil()
    data class DynamicString(val message: String) : TextUtil()

    fun asString(context: Context, vararg args: String): String = when (this) {
        is DynamicString -> message
        is StringResource -> context.getString(resId, *args)
    }
}
