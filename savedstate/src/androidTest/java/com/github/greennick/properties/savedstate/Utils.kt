package com.github.greennick.properties.savedstate

fun checkIf(statement: Boolean, message: String = "") {
    if (!statement) {
        if (message.isEmpty()) {
            throw IllegalStateException()
        } else {
            throw IllegalStateException(message)
        }
    }
}
