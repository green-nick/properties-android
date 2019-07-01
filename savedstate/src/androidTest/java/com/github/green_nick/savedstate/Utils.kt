package com.github.green_nick.savedstate

fun checkIf(statement: Boolean, message: String = "") {
    if (!statement) {
        if (message.isEmpty()) {
            throw IllegalStateException()
        } else {
            throw IllegalStateException(message)
        }
    }
}
