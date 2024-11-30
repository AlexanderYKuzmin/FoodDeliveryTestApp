package com.kuzmin.fooddeliverytestapp.util

import android.content.res.Resources
import com.kuzmin.fooddeliverytestapp.R
import com.kuzmin.fooddeliverytestapp.util.exceptions.UndefinedLocationException

object ErrorHandler {

    private const val REGEX = "\\d{3}"

    fun handleError(throwable: Throwable, resources: Resources): String {
        return when (throwable) {
            is UndefinedLocationException -> {
                resources.getString(R.string.undefined_location)
            }
            else -> {
                val message = throwable.message ?: return resources.getString(R.string.response_error_unknown)

                val regex = REGEX.toRegex()
                if (message.contains(regex)) {
                    val code = regex.find(message)?.value

                    if (code != null) {
                        return when (code.toInt()) {
                            400 -> resources.getString(R.string.response_error_400)
                            401 -> resources.getString(R.string.response_error_401)
                            403 -> resources.getString(R.string.response_error_403)
                            405 -> resources.getString(R.string.response_error_405)
                            413 -> resources.getString(R.string.response_error_413)
                            429 -> resources.getString(R.string.response_error_429)
                            in 500..510-> resources.getString(R.string.response_error_5xx)
                            else -> resources.getString(R.string.response_error_unknown)
                        }
                    }
                }
                message
            }
        }
    }
}