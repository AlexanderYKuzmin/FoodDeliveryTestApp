package com.kuzmin.fooddeliverytestapp.util.exceptions

class UndefinedLocationException : Exception() {

    override val message: String
        get() = getLocalMessage()

    private fun getLocalMessage(): String {
        return "Undefined location"
    }
}