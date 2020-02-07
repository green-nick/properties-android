package com.github.greennick.properties.android

import android.app.Activity
import android.view.View

internal inline fun <reified V : View> Activity.find(id: Int): V {
    val found: View = findViewById(id)
        ?: throw IllegalArgumentException("ID does not reference a View inside this Activity $this")

    return if (found is V) {
        found
    } else {
        throw ClassCastException("View $found does not correspond required type ${V::class.java}")
    }
}